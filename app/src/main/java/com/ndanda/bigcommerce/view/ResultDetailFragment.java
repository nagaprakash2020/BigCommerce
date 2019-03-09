package com.ndanda.bigcommerce.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ndanda.bigcommerce.BigCommerceApplication;
import com.ndanda.bigcommerce.R;
import com.ndanda.bigcommerce.databinding.FragmentResultDetailBinding;
import com.ndanda.bigcommerce.viewmodel.ResultsViewModel;

import javax.inject.Inject;

public class ResultDetailFragment extends Fragment implements View.OnClickListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ResultsDetailFragmentListener mListener;
    private FragmentResultDetailBinding fragmentResultDetailBinding;
    ResultsViewModel resultsViewModel;

    public interface ResultsDetailFragmentListener {
    }

    public ResultDetailFragment() {
        // Required empty public constructor
    }

    public static ResultDetailFragment newInstance() {
        return new ResultDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BigCommerceApplication) getActivity().getApplication()).getApplicationComponent()
                .inject(this);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ResultsDetailFragmentListener) {
            mListener = (ResultsDetailFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ResultsDetailFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentResultDetailBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_result_detail, container, false);

        resultsViewModel = ViewModelProviders.of(getActivity(), viewModelFactory)
                .get(ResultsViewModel.class);

        fragmentResultDetailBinding.setResultsViewModel(resultsViewModel);
        fragmentResultDetailBinding.backButton.setOnClickListener(this);
        fragmentResultDetailBinding.favorite.setSelected(resultsViewModel.getSelectedEvent()
                .getValue().getFavorite());
        fragmentResultDetailBinding.favorite.setOnClickListener(this);
        return fragmentResultDetailBinding.getRoot();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.favorite:
                if (!fragmentResultDetailBinding.favorite.isSelected()) {
                    fragmentResultDetailBinding.favorite.setSelected(true);
                    resultsViewModel.addEventToFavorite();
                } else {
                    fragmentResultDetailBinding.favorite.setSelected(false);
                    resultsViewModel.removeEventFromFavorite();
                }
                break;
            case R.id.back_button:
                getActivity().onBackPressed();
                break;
        }

    }
}
