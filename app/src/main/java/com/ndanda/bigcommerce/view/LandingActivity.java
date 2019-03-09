package com.ndanda.bigcommerce.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ndanda.bigcommerce.BigCommerceApplication;
import com.ndanda.bigcommerce.R;
import com.ndanda.bigcommerce.data.events;
import com.ndanda.bigcommerce.databinding.ActivityLandingBinding;
import com.ndanda.bigcommerce.viewmodel.ResultsViewModel;

import java.util.Stack;

import javax.inject.Inject;

public class LandingActivity extends AppCompatActivity implements
        SearchFragment.OnSearchFragmentInteractionListener,
        ResultDetailFragment.ResultsDetailFragmentListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    ActivityLandingBinding activityLandingBinding;
    ResultsViewModel resultsViewModel;
    SearchFragment searchFragment;
    private Stack<Fragment> fragmentStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ((BigCommerceApplication) getApplication())
                .getApplicationComponent()
                .inject(this);
        super.onCreate(savedInstanceState);

        populateFragmentStack(savedInstanceState);

        activityLandingBinding = DataBindingUtil.setContentView(this,R.layout.activity_landing);
        resultsViewModel = ViewModelProviders.of(this, viewModelFactory)
                            .get(ResultsViewModel.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showSearchFragment();
    }

    @Override
    public void onSearchItemResultSelected(events event) {

        // Update the selected Value in view Model.
        resultsViewModel.getSelectedEvent().setValue(event);
        ResultDetailFragment detailFragment = ResultDetailFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, detailFragment,ResultDetailFragment.class.getName());
        fragmentStack.lastElement().onPause();
        fragmentTransaction.hide(fragmentStack.lastElement());
        fragmentStack.push(detailFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        if (fragmentStack.size() == 2) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentStack.lastElement().onPause();
            fragmentTransaction.remove(fragmentStack.pop());
            fragmentStack.lastElement().onResume();
            fragmentTransaction.show(fragmentStack.lastElement());
            fragmentTransaction.commit();
        } else {
            super.onBackPressed();
        }
    }

    private void populateFragmentStack(Bundle savedInstanceState) {
        fragmentStack = new Stack<>();
        if (savedInstanceState != null) {
            for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
                Fragment fragment = getSupportFragmentManager().getFragments().get(i);
                if (fragment instanceof SearchFragment || fragment instanceof ResultDetailFragment)
                    fragmentStack.push(getSupportFragmentManager().getFragments().get(i));
            }
        }
    }

    private void showSearchFragment() {

        searchFragment = (SearchFragment) getSupportFragmentManager()
                .findFragmentByTag(SearchFragment.class.getName());

        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, searchFragment,SearchFragment.class.getName());
            fragmentStack.push(searchFragment);
            fragmentTransaction.commit();
        }

    }
}