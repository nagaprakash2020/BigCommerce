package com.ndanda.bigcommerce.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ndanda.bigcommerce.repository.BigCommerceRepository;

import javax.inject.Singleton;

@Singleton
public class BigCommerceViewModelFactory implements ViewModelProvider.Factory {

    private final BigCommerceRepository repository;

    public BigCommerceViewModelFactory(BigCommerceRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ResultsViewModel.class))
            return (T) new ResultsViewModel(repository);
        else
            throw new IllegalArgumentException("ViewModel Not Found");

    }
}
