package com.ndanda.bigcommerce.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ndanda.bigcommerce.BigCommerceApplication;
import com.ndanda.bigcommerce.R;
import com.ndanda.bigcommerce.data.events;
import com.ndanda.bigcommerce.databinding.FragmentSearchBinding;
import com.ndanda.bigcommerce.utils.UIUtils;
import com.ndanda.bigcommerce.viewmodel.ResultsViewModel;

import java.util.List;

import javax.inject.Inject;

public class SearchFragment extends Fragment implements ResultsAdapter.ResultsClickListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private OnSearchFragmentInteractionListener mListener;
    FragmentSearchBinding fragmentSearchBinding;
    ResultsAdapter resultsAdapter;
    Context context;
    ResultsViewModel resultsViewModel;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSearchFragmentInteractionListener) {
            this.context = context;
            mListener = (OnSearchFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSearchFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BigCommerceApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search,
                container, false);
        resultsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ResultsViewModel.class);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);

        resultsAdapter = new ResultsAdapter(context, this);
        fragmentSearchBinding.searchResultsList.setLayoutManager(mLayoutManager);
        // ItemDecoration for each item in the list
        fragmentSearchBinding.searchResultsList.addItemDecoration(
                new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        fragmentSearchBinding.searchResultsList.setAdapter(resultsAdapter);
        fragmentSearchBinding.searchBar.addTextChangedListener(new CustomTextChangeListener());

        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity() != null)
            UIUtils.hideKeyboard(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void updateSearchResults(List<events> eventsList) {
        resultsAdapter.setEventsList(eventsList);
    }

    @Override
    public void onResultItemClicked(events event) {
        mListener.onSearchItemResultSelected(event);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSearchFragmentInteractionListener {
        void onSearchItemResultSelected(events event);
    }

    private class CustomTextChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (isAdded()) {
                resultsViewModel.setSearchString(s.toString());

                resultsViewModel.getSearchResultsWithFavorites().observe(SearchFragment.this,
                        SearchFragment.this::updateSearchResults);
            }

        }
    }
}
