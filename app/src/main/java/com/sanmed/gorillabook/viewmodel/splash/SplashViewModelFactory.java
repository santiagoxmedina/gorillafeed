package com.sanmed.gorillabook.viewmodel.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sanmed.gorillabook.model.database.FeedDataBaseDAO;


public class SplashViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private FeedDataBaseDAO  mDataBaseDAO;
    public SplashViewModelFactory(Application application, FeedDataBaseDAO dataBaseDAO){
        mApplication = application;
        mDataBaseDAO = dataBaseDAO;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(SplashViewModel.class)){
            return (T) new SplashViewModel(mApplication,mDataBaseDAO);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
