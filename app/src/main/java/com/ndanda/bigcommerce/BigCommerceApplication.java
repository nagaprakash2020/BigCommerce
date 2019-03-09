package com.ndanda.bigcommerce;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.ndanda.bigcommerce.di.ApplicationComponent;
import com.ndanda.bigcommerce.di.ApplicationModule;
import com.ndanda.bigcommerce.di.DaggerApplicationComponent;

/**
 * Created by ndanda on 4/8/2018.
 */

public class BigCommerceApplication extends Application{

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

    }

    public ApplicationComponent getApplicationComponent() {
         return applicationComponent;
    }
}
