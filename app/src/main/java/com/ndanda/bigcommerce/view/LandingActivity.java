package com.ndanda.bigcommerce.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ndanda.bigcommerce.BigCommerceApplication;
import com.ndanda.bigcommerce.R;
import com.ndanda.bigcommerce.data.events;
import com.ndanda.bigcommerce.databinding.ActivityLandingBinding;
import com.ndanda.bigcommerce.viewmodel.ResultsViewModel;

import java.util.Stack;

import javax.inject.Inject;

/**
 * This is the launcher activity.
 * Initially shows the {@link SearchFragment} fragment.
 *
 */
public class LandingActivity extends AppCompatActivity implements
        SearchFragment.OnSearchFragmentInteractionListener,
        EventDetailFragment.ResultsDetailFragmentListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    CountingIdlingResource countingIdlingResource;

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

        activityLandingBinding = DataBindingUtil.setContentView(this, R.layout.activity_landing);
        resultsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ResultsViewModel.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showSearchFragment();
    }


    /**
     *  Upon user clicking on a event.
     *
     * set selected event as the SelectedEvent in {@link ResultsViewModel}
     * Open {@link EventDetailFragment}
     * instance of {@link EventDetailFragment} is also pushed to {@link fragmentStack}
     * @param event selected by the user.
     */
    @Override
    public void onSearchItemResultSelected(events event) {

        // Update the selected Value in view Model.
        resultsViewModel.getSelectedEvent().setValue(event);
        EventDetailFragment detailFragment = EventDetailFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, detailFragment,
                EventDetailFragment.class.getName());
        fragmentStack.lastElement().onPause();
        fragmentTransaction.hide(fragmentStack.lastElement());
        fragmentStack.push(detailFragment);
        fragmentTransaction.commit();
    }


    /**
     *  if {@link fragmentStack} has more than 2 items,
     *  Pause and pop out the last fragment. Which in our case would be {@link ResultDetailFragment}
     *  and show 1st fragment. Which would be {@link SearchFragment}
     */
    @Override
    public void onBackPressed() {
        if (fragmentStack.size() == 2) {

            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentStack.lastElement().onPause();
            fragmentTransaction.remove(fragmentStack.pop());
            fragmentStack.lastElement().onResume();
            fragmentTransaction.show(fragmentStack.lastElement());
            fragmentTransaction.commit();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Method to keep track of all fragment from the fragment manager.
     * This data will be used to bring up fragments from stack when user navigates from one screen
     * to other.
     * @param savedInstanceState
     */
    private void populateFragmentStack(Bundle savedInstanceState) {
        fragmentStack = new Stack<>();
        if (savedInstanceState != null) {
            for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
                Fragment fragment = getSupportFragmentManager().getFragments().get(i);
                if (fragment instanceof SearchFragment || fragment instanceof EventDetailFragment)
                    fragmentStack.push(getSupportFragmentManager().getFragments().get(i));
            }
        }
    }

    /**
     *  Method to show {@link SearchFragment} to user.
     */
    private void showSearchFragment() {

        searchFragment = (SearchFragment) getSupportFragmentManager()
                .findFragmentByTag(SearchFragment.class.getName());

        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance();
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, searchFragment,
                    SearchFragment.class.getName());
            fragmentStack.push(searchFragment);
            fragmentTransaction.commit();
        }

    }

    public CountingIdlingResource getIdlingResource(){
        return countingIdlingResource;
    }
}