package com.ndanda.bigcommerce.api;

import android.arch.lifecycle.LiveData;

import com.ndanda.bigcommerce.data.SeatGeekEvent;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ndanda on 4/10/2018.
 */

public interface ApiService {

    /**
     * @param searchString entered by user
     * @return
     */
    @GET("events")
    LiveData<ApiResponse<SeatGeekEvent>> getEvents(@Query("q") String searchString);
}
