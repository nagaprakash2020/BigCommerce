package com.ndanda.bigcommerce.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.ndanda.bigcommerce.api.ApiResponse;
import com.ndanda.bigcommerce.data.SeatGeekEvent;
import com.ndanda.bigcommerce.data.events;
import com.ndanda.bigcommerce.repository.BigCommerceRepository;
import com.ndanda.bigcommerce.utils.AbsentLiveData;

import java.util.List;
import java.util.Locale;

public class ResultsViewModel extends ViewModel {

    private BigCommerceRepository repository;
    private LiveData<List<events>> favoriteEvents;
    private LiveData<ApiResponse<SeatGeekEvent>> searchResultEvents;
    private MediatorLiveData<List<events>> searchResultsWithFavorites = new MediatorLiveData<>();
    private MutableLiveData<String> searchString = new MutableLiveData<>();
    private MutableLiveData<events> selectedEvent = new MutableLiveData<>();

    public ResultsViewModel(BigCommerceRepository repository) {
        this.repository = repository;

        // Get favorite events
        favoriteEvents = repository.getFavoriteEvents();

        /**
         *  @searchResultEvents will be updated when there is a change in @searchString
         */
        searchResultEvents = Transformations.switchMap(searchString, search -> {
            if(searchString.getValue() == null || searchString.getValue().isEmpty()){
                return AbsentLiveData.create();
            }else
                return repository.getResults(searchString.getValue());
        });


        /**
         *  @searchResultsWithFavorites will be updated when there is a change in @searchResultEvents
         */
        searchResultsWithFavorites.addSource(searchResultEvents, search -> {
            if (searchResultEvents != null && searchResultEvents.getValue() != null && searchResultEvents.getValue().body != null) {
                if (favoriteEvents == null || favoriteEvents.getValue() == null) {
                    searchResultsWithFavorites.setValue(searchResultEvents.getValue().body.getEvents());
                } else {

                    for (int i = 0; i < searchResultEvents.getValue().body.getEvents().size(); i++) {
                        if (favoriteEvents.getValue().contains(searchResultEvents.getValue().body.getEvents().get(i))) {
                            searchResultEvents.getValue().body.getEvents().get(i).setFavorite(true);
                        }
                    }

                    searchResultsWithFavorites.setValue(searchResultEvents.getValue().body.getEvents());
                }
            }else if(searchResultEvents != null && searchResultEvents.getValue() == null){
                searchResultsWithFavorites.setValue(null);
            }
        });


        /**
         *  @searchResultsWithFavorites will be updated when there is a change in @favoriteEvents
         */
        searchResultsWithFavorites.addSource(favoriteEvents, search -> {
            if (searchResultEvents != null && searchResultEvents.getValue() != null && searchResultEvents.getValue().body != null) {
                if (favoriteEvents == null || favoriteEvents.getValue() == null) {
                    searchResultsWithFavorites.setValue(searchResultEvents.getValue().body.getEvents());
                } else {

                    for (int i = 0; i < searchResultEvents.getValue().body.getEvents().size(); i++) {
                        if (favoriteEvents.getValue().contains(searchResultEvents.getValue().body.getEvents().get(i))) {
                            searchResultEvents.getValue().body.getEvents().get(i).setFavorite(true);
                        }
                    }

                    searchResultsWithFavorites.setValue(searchResultEvents.getValue().body.getEvents());
                }
            }
        });

    }

    public void setSearchString(@NonNull String query) {
        String input = query.toLowerCase(Locale.getDefault()).trim();
        searchString.setValue(input);
    }

    public MutableLiveData<events> getSelectedEvent() {
        return selectedEvent;
    }

    public LiveData<List<events>> getSearchResultsWithFavorites() {
        return searchResultsWithFavorites;
    }

    @VisibleForTesting
    public LiveData<ApiResponse<SeatGeekEvent>> getSearchResultEvents() {
        return searchResultEvents;
    }

    public void setSearchResultEvents(LiveData<ApiResponse<SeatGeekEvent>> searchResultEvents) {
        this.searchResultEvents = searchResultEvents;
    }

    public void addEventToFavorite() {
        if (selectedEvent != null && selectedEvent.getValue() != null) {
            events event = selectedEvent.getValue();
            event.setFavorite(true);
            repository.saveFavoriteEvent(selectedEvent.getValue());
        }
    }

    public void removeEventFromFavorite() {
        if (selectedEvent != null && selectedEvent.getValue() != null) {
            events event = selectedEvent.getValue();
            event.setFavorite(false);
            repository.removeEventFromFavorite(selectedEvent.getValue());
        }
    }

}
