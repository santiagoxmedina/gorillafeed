package com.sanmed.gorillabook.viewmodel.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sanmed.gorillabook.model.GorillaFeedRepository;
import com.sanmed.gorillabook.model.database.FeedDataBaseDAO;

public class SplashViewModel extends AndroidViewModel {


    private final GorillaFeedRepository gorillaBookRepository;
    private  boolean isInitialized;
    public SplashViewModel(@NonNull Application application, FeedDataBaseDAO dataBaseDAO) {
        super(application);
        gorillaBookRepository = new GorillaFeedRepository(application,dataBaseDAO);
        isInitialized = false;
    }

    public void init() {
        if(!isInitialized){
            isInitialized = true;
            gorillaBookRepository.updateFeed();
        }
    }


    public LiveData<Boolean> getDataLoadedSuccess() {
        return gorillaBookRepository.getDataLoadedSuccess();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        gorillaBookRepository.onCleared();
    }
}
