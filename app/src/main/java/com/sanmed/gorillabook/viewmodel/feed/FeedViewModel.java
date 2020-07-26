package com.sanmed.gorillabook.viewmodel.feed;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sanmed.gorillabook.model.GorillaFeedRepository;
import com.sanmed.gorillabook.model.database.FeedDataBaseDAO;
import com.sanmed.gorillabook.utils.DateUtils;
import com.sanmed.gorillabook.view.common.FeedUI;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FeedViewModel extends AndroidViewModel {

    public MutableLiveData<String> dateStringLiveData;
    public MutableLiveData<List<FeedUI>> feeds;
    private final GorillaFeedRepository gorillaBookRepository;
    private boolean initialized;
    private final MutableLiveData<Boolean> gotToCreatePost;

    public FeedViewModel(Application application, FeedDataBaseDAO dataBaseDAO) {
        super(application);
        gorillaBookRepository = new GorillaFeedRepository(application,dataBaseDAO);
        dateStringLiveData = new MutableLiveData<>(getCurrentDate());
        gotToCreatePost = new MutableLiveData<>(false);
        initialized = false;
    }

    public void init(){
        if(!initialized){
            initialized = true;
            gorillaBookRepository.loadFeedUIs();
        }
    }

    private String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        return DateUtils.stringDate(c,DateUtils.patternDate);
    }

    public LiveData<List<FeedUI>> getFeeds() {
        return gorillaBookRepository.getFeeds();
    }

    public LiveData<String> getDate() {
        return dateStringLiveData;
    }

    public void onAddFeedClick(){
        gotToCreatePost.setValue(true);
    }

    public LiveData<Boolean> getGotToCreatePost() {
        return gotToCreatePost;
    }

    public void onGotToCreatePostCompleted(){
        gotToCreatePost.setValue(false);
    }
}