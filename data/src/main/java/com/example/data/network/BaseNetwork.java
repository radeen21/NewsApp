package com.example.data.network;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseNetwork<T> {

    private T networkService;

    protected abstract Class<T> getNetworkClass();

    protected abstract String getBaseUrl();

    protected Request addheader(Request request) {
        return request;
    }

    public T getNetworkService() {
        if (networkService == null) {
            initNetworkInterface();
        }
        return networkService;
    }

    private void initNetworkInterface() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request();
            return chain.proceed(addheader(request));
        }).build();
        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        networkService = retrofit.create(getNetworkClass());
    }
}

