package com.sanmed.gorillabook.viewmodel.feed;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sanmed.gorillabook.model.database.FeedDataBaseDAO;
import com.sanmed.gorillabook.viewmodel.splash.SplashViewModel;

public class FeedViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private FeedDataBaseDAO mDataBaseDAO;

    public FeedViewModelFactory(Application application, FeedDataBaseDAO dataBaseDAO) {
        mApplication = application;
        mDataBaseDAO = dataBaseDAO;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FeedViewModel.class)) {
            return (T) new FeedViewModel(mApplication, mDataBaseDAO);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
