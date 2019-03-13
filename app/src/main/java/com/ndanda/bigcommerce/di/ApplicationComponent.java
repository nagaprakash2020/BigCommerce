package com.ndanda.bigcommerce.di;

import android.app.Application;

import com.ndanda.bigcommerce.BigCommerceApplication;
import com.ndanda.bigcommerce.view.LandingActivity;
import com.ndanda.bigcommerce.view.EventDetailFragment;
import com.ndanda.bigcommerce.view.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Created by ndanda on 3/09/2019.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent extends AndroidInjector<BigCommerceApplication>{

    Application exposeApplication();
    void inject(LandingActivity landingActivity);
    void inject(EventDetailFragment eventDetailFragment);
    void inject(SearchFragment searchFragment);
}
