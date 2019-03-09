package com.ndanda.bigcommerce.di;

import android.app.Application;

import com.ndanda.bigcommerce.BigCommerceApplication;
import com.ndanda.bigcommerce.view.LandingActivity;
import com.ndanda.bigcommerce.view.ResultDetailFragment;
import com.ndanda.bigcommerce.view.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Created by ndanda on 4/8/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent extends AndroidInjector<BigCommerceApplication>{

    Application exposeApplication();
    void inject(LandingActivity landingActivity);
    void inject(ResultDetailFragment resultDetailFragment);
    void inject(SearchFragment searchFragment);
}
