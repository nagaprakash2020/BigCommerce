package com.ndanda.bigcommerce.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ndanda.bigcommerce.AppExecutors;
import com.ndanda.bigcommerce.api.ApiResponse;
import com.ndanda.bigcommerce.api.ApiService;
import com.ndanda.bigcommerce.data.SeatGeekEvent;
import com.ndanda.bigcommerce.data.events;
import com.ndanda.bigcommerce.vo.Resource;

import java.util.List;

import javax.inject.Inject;

public class BigCommerceRepository {

    private final AppExecutors appExecutors;
    private final ApiService apiService;
    private FavouriteDao favouriteDao;

    @Inject
    public BigCommerceRepository(AppExecutors appExecutors, ApiService apiService, FavouriteDao favouriteDao) {
        this.appExecutors = appExecutors;
        this.apiService = apiService;
        this.favouriteDao = favouriteDao;
    }

    public LiveData<ApiResponse<SeatGeekEvent>> getResults(String searchString){
        return apiService.getEvents(searchString);
    }

    public void saveFavoriteEvent(events event){
        try{
            Thread t = new Thread(() -> favouriteDao.insertFavoriteEvent(event));
            t.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeEventFromFavorite(events event){
        try{
            Thread t = new Thread(() -> favouriteDao.removeEventFromFavorite(event));
            t.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public LiveData<List<events>> getFavoriteEvents(){
        return favouriteDao.getFavoriteEvents();
    }
}
