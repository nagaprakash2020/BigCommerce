package com.ndanda.bigcommerce.view;


import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ndanda.bigcommerce.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchResultFragmentTest {


    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<LandingActivity> mActivityTestRule =
            new ActivityTestRule<>(LandingActivity.class);

    /**
     * Use {@link ActivityScenario to launch and get access to the activity.
     * {@link ActivityScenario#onActivity(ActivityScenario.ActivityAction)} provides a thread-safe
     * mechanism to access the activity.
     */
    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        IdlingRegistry.getInstance().register(mIdlingResource);
    }


    /**
     * Test to check if details fragment is displayed
     * <p>
     * 1. Click on searchBar
     * 2. Enter text "Miami"
     * 3. Click on 1st item.
     * 4. Check details fragment is displayed
     */
    @Test
    public void checkDetailsFragmentIsDisplayedTest() {

        // Enter "Miami" on searchbar
        onView(withId(R.id.search_bar)).perform(replaceText("Miami"));
        // Click on 1st item
        onView(withId(R.id.search_results_list)).perform(actionOnItemAtPosition(0, click()));

        // DetailsFragment is displayed
        onView(withId(R.id.result_details_layout)).check(matches(isDisplayed()));
    }

    /**
     * Check Favorite button is displayed in {@link SearchFragment} when user favorites
     * event from {@link EventDetailFragment}
     * <p>
     * Steps
     * 1. Enter text "Texas" in searchbar
     * 2. Click on 1st item on recyler view
     * 3. Check if favorite button is displayed.
     */
    @Test
    public void checkFavoriteIsDisplayedTest() {
        // Enter "Miami" on searchbar
        onView(withId(R.id.search_bar)).perform(replaceText("Texas"));
        // Click on 1st item
        onView(withId(R.id.search_results_list)).perform(actionOnItemAtPosition(0, click()));
        // Click on favorite image
        onView(withId(R.id.favorite)).check(matches(isDisplayed()));
    }

    /**
     * UnRegister the idlingResource
     */
    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
