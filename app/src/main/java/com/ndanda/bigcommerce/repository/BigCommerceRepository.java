package com.ndanda.bigcommerce.repository;

import android.arch.lifecycle.LiveData;

import com.ndanda.bigcommerce.api.ApiResponse;
import com.ndanda.bigcommerce.api.ApiService;
import com.ndanda.bigcommerce.data.SeatGeekEvent;
import com.ndanda.bigcommerce.data.events;

import java.util.List;

import javax.inject.Inject;

public class BigCommerceRepository {

    private final ApiService apiService;
    private FavouriteDao favouriteDao;

    @Inject
    public BigCommerceRepository(ApiService apiService, FavouriteDao favouriteDao) {
        this.apiService = apiService;
        this.favouriteDao = favouriteDao;
    }

    /**
     *
     * @param searchString enter by user
     * @return SeatGeekEvent result from the api call
     */
    public LiveData<ApiResponse<SeatGeekEvent>> getResults(String searchString){
        return apiService.getEvents(searchString);
    }

    /**
     * event will be inserted into database.
     * @param event to be added as favorite
     */
    public void saveFavoriteEvent(events event){
        try{
            Thread t = new Thread(() -> favouriteDao.insertFavoriteEvent(event));
            t.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * event will be removed from database.
     * @param event to be removed from favorites
     */
    public void removeEventFromFavorite(events event){
        try{
            Thread t = new Thread(() -> favouriteDao.removeEventFromFavorite(event));
            t.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return the list of favorite events from database
     */
    public LiveData<List<events>> getFavoriteEvents(){
        return favouriteDao.getFavoriteEvents();
    }
}
