package com.sanmed.gorillabook.model.client;

import com.google.gson.GsonBuilder;
import com.sanmed.gorillabook.model.models.FeedResponse;
import com.sanmed.gorillabook.model.service.GorillaService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class GorillaClient {

    private final  String url = "https://gl-endpoint.herokuapp.com/";

    private  GorillaService service;
    private static GorillaClient instance;

    private GorillaClient() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()));

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            builder.client(client);

        final Retrofit retrofit = builder.build();
        service = retrofit.create(GorillaService.class);
    }

    public static GorillaClient getInstance() {
        if (instance == null) {
            instance = new GorillaClient();
        }
        return instance;
    }

    public Observable<Response<FeedResponse[]>> getFeed(){
        return service.getFeed();
    }
}
