package com.ndanda.bigcommerce.viewmodel;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.ndanda.bigcommerce.api.ApiResponse;
import com.ndanda.bigcommerce.data.SeatGeekEvent;
import com.ndanda.bigcommerce.data.events;
import com.ndanda.bigcommerce.repository.BigCommerceRepository;
import com.ndanda.bigcommerce.testUtils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResultsViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    BigCommerceRepository bigCommerceRepository;

    @Mock
    Observer<ApiResponse<SeatGeekEvent>> apiEventResultsObserver;

    @Mock
    private Application mContext;

    private ResultsViewModel resultsViewModel;

    @Before
    public void setUpResultsViewModel() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        resultsViewModel = new ResultsViewModel(bigCommerceRepository);
        setUpContext();
    }

    private void setUpContext() {

        when(mContext.getApplicationContext()).thenReturn(mContext);

        // Create Fake events and add them to LiveData

        List<events> eventsList = new ArrayList<>();
        eventsList.add(new events());
        eventsList.add(new events());
        eventsList.add(new events());
        eventsList.add(new events());

        SeatGeekEvent seatGeekEvent = new SeatGeekEvent();
        seatGeekEvent.setEvents(eventsList);

        Response<SeatGeekEvent> fakeResponse = Response.success(seatGeekEvent);


        LiveData<ApiResponse<SeatGeekEvent>> fakeApiResults = new MutableLiveData<>();
        ((MutableLiveData<ApiResponse<SeatGeekEvent>>) fakeApiResults).
                setValue(new ApiResponse<>(fakeResponse));

        when(bigCommerceRepository.getResults(anyString())).thenReturn(fakeApiResults);
    }

    /**
     *  1. Create an event.
     *  2. Mark that as an selected event.
     *  3. Call addEventToFavorite() on resultsViewModel class and
     *  check if "saveFavoriteEvent()" method is called at-least once on
     *  bigCommerceRepository class.
     */
    @Test
    public void addEventToFavoriteTest() {
        // Check if saveFavorite() is called on bigCommerceRepository.

        final int fakeId = 121;
        // Create a Fake event and make it a selectedEvent.
        events event1 = new events();
        event1.setId(fakeId);
        MutableLiveData<events> selectedEvent = new MutableLiveData<>();
        selectedEvent.setValue(event1);
        resultsViewModel.getSelectedEvent().setValue(event1);

        // Call addEventToFavorite method.
        resultsViewModel.addEventToFavorite();

        // Check if saveFavoriteEvent method of BigCommerceRepository is called.
        verify(bigCommerceRepository, atLeastOnce()).saveFavoriteEvent(event1);

    }

    /**
     *  1. Create an event.
     *  2. Mark that as an selected event.
     *  3. Call removeEventFromFavorite() on resultsViewModel class and
     *  check if "removeEventFromFavorite()" method is called at-least once on
     *  bigCommerceRepository class.
     */
    @Test
    public void removeEventFromFavoriteTest() {

        // Check if removeEventFromFavorite() is called on bigCommerceRepository

        final int fakeId = 121;
        // Create a Fake event and make it a selectedEvent.
        events event1 = new events();
        event1.setId(fakeId);
        MutableLiveData<events> selectedEvent = new MutableLiveData<>();
        selectedEvent.setValue(event1);
        resultsViewModel.getSelectedEvent().setValue(event1);

        // Call removeEventFromFavorite method.
        resultsViewModel.removeEventFromFavorite();

        // Check if removeEventFromFavorite method of BigCommerceRepository is called.
        verify(bigCommerceRepository, atLeastOnce()).removeEventFromFavorite(event1);
    }


    /**
     * Verify searchResults are updated when search string is changed.
     */
    @Test
    public void verifySearchResults() throws IOException, InterruptedException {

        // Add a dummy observer to SearchResultsWithFavorites

        resultsViewModel.getSearchResultEvents().observeForever(apiEventResultsObserver);

        // Update searchString
        resultsViewModel.setSearchString("Doesn't matter");


        // Check searchResultEvents value is updated
        ApiResponse<SeatGeekEvent> searchResultEvents =
                LiveDataTestUtil.getValue(resultsViewModel.getSearchResultEvents());

        // 4 events are added during the setup of this test.Verify they are returned when
        // searchString is updated.
        assertThat(searchResultEvents.body.getEvents(), hasSize(4));

    }
}