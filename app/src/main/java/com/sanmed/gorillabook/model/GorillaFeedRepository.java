package com.sanmed.gorillabook.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.sanmed.gorillabook.model.client.GorillaClient;
import com.sanmed.gorillabook.model.database.Feed;
import com.sanmed.gorillabook.model.database.FeedDataBaseDAO;
import com.sanmed.gorillabook.model.models.FeedHelper;
import com.sanmed.gorillabook.model.models.FeedResponse;
import com.sanmed.gorillabook.view.common.FeedUI;

import java.util.List;

import io.reactivex.internal.subscribers.BlockingBaseSubscriber;
import retrofit2.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GorillaFeedRepository {

    private Subscription subscription;
    private Application mApplication;
    private  final MutableLiveData<Boolean> dataLoadedSuccess;
    private  final MutableLiveData<String> message;
    private  final MediatorLiveData<List<FeedUI>> feedsUI;
    private  FeedDataBaseDAO mDataBaseDAO;

    public GorillaFeedRepository(@NonNull Application application, FeedDataBaseDAO dataBaseDAO) {
        mApplication = application;
        dataLoadedSuccess = new MutableLiveData<>(false);
        message = new MutableLiveData<>("");
        mDataBaseDAO = dataBaseDAO;
        feedsUI = new MediatorLiveData<>();
    }

    public LiveData<Boolean> getDataLoadedSuccess() {
        return dataLoadedSuccess;
    }

    public void updateFeed() {
        subscription = GorillaClient.getInstance()
                .getFeed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onResponseFeed);
    }

    private void onResponseFeed(Response<FeedResponse[]> response) {
        if(response.isSuccessful()){
            saveData(response.body());
        }else{
            message.setValue(response.errorBody().toString());
        }
    }


    private void saveData(FeedResponse[] feedResponses) {

      mDataBaseDAO.saveFeed(FeedHelper.parseResponses(feedResponses)).subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(io.reactivex.schedulers.Schedulers.io()).subscribe(this::onSavedCompleted);
    }

    private void onSavedCompleted() {
        dataLoadedSuccess.postValue(true);
    }

    public void onCleared() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public LiveData<List<FeedUI>> getFeeds() {
            return feedsUI;
    }

    public void loadFeedUIs() {
//        feedsUI.addSource(mDataBaseDAO.getAllFeed(),this::onFeedChanged);
        mDataBaseDAO.getAllFeed().subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(io.reactivex.schedulers.Schedulers.io()).subscribe(new BlockingBaseSubscriber<List<Feed>>() {
            @Override
            public void onNext(List<Feed> feeds) {
                onFeedChanged(feeds);
            }

            @Override
            public void onError(Throwable t) {
            message.setValue(t.getMessage());
            }
        });
    }

    private void onFeedChanged(List<Feed> feeds) {
        feedsUI.postValue(FeedHelper.parseFeeds(feeds));
    }
}
