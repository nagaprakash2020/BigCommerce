package com.ndanda.bigcommerce.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.support.test.espresso.idling.CountingIdlingResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ndanda.bigcommerce.AppExecutors;
import com.ndanda.bigcommerce.BigCommerceApplication;
import com.ndanda.bigcommerce.api.ApiService;
import com.ndanda.bigcommerce.repository.BigCommerceRepository;
import com.ndanda.bigcommerce.repository.FavouriteDao;
import com.ndanda.bigcommerce.repository.BigCommerceDatabase;
import com.ndanda.bigcommerce.utils.LiveDataCallAdapterFactory;
import com.ndanda.bigcommerce.utils.RetrofitApiKeyInterceptor;
import com.ndanda.bigcommerce.viewmodel.BigCommerceViewModelFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ndanda on 3/09/2019.
 */

@Module
public class ApplicationModule {

    private final BigCommerceApplication application;

    public ApplicationModule(BigCommerceApplication bigCommerceApplication){
        application = bigCommerceApplication;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(BigCommerceRepository repository){
        return new BigCommerceViewModelFactory(repository);
    }

    @Provides
    @Singleton
    AppExecutors providesAppExecutors(){
        return new AppExecutors();
    }


    @Provides
    @Named("dataBase")
    String providesDatabasePath(){
        return "BigCommerce.db";
    }

    @Singleton
    @Provides
    BigCommerceDatabase provideBigCommerceDatabase(Application application, @Named("dataBase") String databaseName){
        return Room.databaseBuilder(
                application,
                BigCommerceDatabase.class,
                databaseName)
                .fallbackToDestructiveMigration()
                .build();
    }


    @Singleton
    @Provides
    FavouriteDao provideFavoriteDao(BigCommerceDatabase bigCommerceDatabase) {
        return bigCommerceDatabase.favouriteDao();
    }

    @Provides
    @Singleton
    BigCommerceRepository provideBigCommerceRepository(AppExecutors appExecutors, ApiService apiService, FavouriteDao favouriteDao){
        return new BigCommerceRepository(appExecutors,apiService,favouriteDao);
    }

    @Provides
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClientBuilder(Interceptor apiKeyInterceptor){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(apiKeyInterceptor);
        httpClient.addInterceptor(logging);

        return httpClient;
    }

    @Provides
    @Named("baseUrl")
    String provideBaseUrl(){
        return "https://api.seatgeek.com/2/";
    }

    @Provides
    @Singleton
    Retrofit provideRetroFit(OkHttpClient.Builder okHttpClientBuilder, @Named("baseUrl") String baseUrl){


        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(okHttpClientBuilder.build())
                .build();

    }

    @Provides
    @Singleton
    Interceptor provideApiKeyInterceptor(){
        return new RetrofitApiKeyInterceptor();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    CountingIdlingResource provideIdlingResource(){
        return new CountingIdlingResource("NetworkCall");
    }
}
