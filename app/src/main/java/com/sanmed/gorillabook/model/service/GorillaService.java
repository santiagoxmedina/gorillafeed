package com.sanmed.gorillabook.model.service;

import com.sanmed.gorillabook.model.models.FeedResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

public interface GorillaService {

    @GET("feed")
    Observable<Response<FeedResponse[]>> getFeed();
}
