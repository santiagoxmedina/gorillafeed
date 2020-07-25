package com.sanmed.gorillabook.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sanmed.gorillabook.model.client.GorillaClient;
import com.sanmed.gorillabook.model.database.FeedDataBaseDAO;
import com.sanmed.gorillabook.model.models.FeedHelper;
import com.sanmed.gorillabook.model.models.FeedResponse;

import retrofit2.HttpException;
import retrofit2.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GorillaFeedRepository {

    private Subscription subscription;
    private Application mApplication;
    private  final MutableLiveData<Boolean> dataLoadedSuccess;
    private  final MutableLiveData<String> message;
    private  boolean isInitialized;
    private  FeedDataBaseDAO mDataBaseDAO;
    public GorillaFeedRepository(@NonNull Application application, FeedDataBaseDAO dataBaseDAO) {
        mApplication = application;
        dataLoadedSuccess = new MutableLiveData<>(false);
        message = new MutableLiveData<>("");
        isInitialized = false;
        mDataBaseDAO = dataBaseDAO;
    }

    public LiveData<Boolean> getDataLoadedSuccess() {
        return dataLoadedSuccess;
    }

    public void init() {
        if(!isInitialized){
            isInitialized = true;
            downloadBooks();
        }
    }

    private void downloadBooks() {
        subscription = GorillaClient.getInstance()
                .getFeed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<FeedResponse[]>>() {
                    @Override public void onCompleted() {
                    }

                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        if(e instanceof HttpException){
                            HttpException httpException = (HttpException)e;
                            message.setValue(httpException.message());
                        }else{
                            message.setValue(e.getMessage());
                        }
                    }

                    @Override public void onNext(Response<FeedResponse[]> response) {
                        OnResponse(response);
                    }
                });
    }

    private void OnResponse(Response<FeedResponse[]> response) {
        if(response.isSuccessful()){
            saveData(response.body());
        }else{
            message.setValue(response.errorBody().toString());
        }
    }

    private void saveData(FeedResponse[] feedResponses) {
        mDataBaseDAO.saveFeed(FeedHelper.parseResponses(feedResponses)).observeOn(Schedulers.io()).subscribe(this::onSavedCompleted);
    }

    private void onSavedCompleted() {
        dataLoadedSuccess.setValue(true);
    }

    public void onCleared() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
