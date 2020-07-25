package com.sanmed.gorillabook.viewmodel.feed;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sanmed.gorillabook.model.database.FeedDataBaseDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FeedViewModel extends AndroidViewModel {

    public MutableLiveData<String> dateStringLiveData;

    public FeedViewModel(Application application, FeedDataBaseDAO dataBaseDAO) {
        super(application);
        dateStringLiveData = new MutableLiveData<>(getCurrentDate());
    }

    private String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("DD ,MMM dd", Locale.getDefault());
        return df.format(c);
    }


    public LiveData<String> getDate() {
        return dateStringLiveData;
    }
}