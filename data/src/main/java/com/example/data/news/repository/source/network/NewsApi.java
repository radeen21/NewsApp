package com.example.data.news.repository.source.network;

import com.example.data.news.NewsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NewsApi {

    @GET("/v2/sources")
    Observable<NewsResponse> getNewsSource(@Query("apiKey") String apiKey);

    @GET("/v2/everything")
    Observable<NewsResponse> getNewsArticle(@Query("sources") String domain,
                                            @Query("apiKey") String apiKey,
                                            @Query("page") int page);

    @GET("/v2/everything")
    Observable<NewsResponse> searchArticle(@Query("q") String keyword,
                                           @Query("apiKey") String apiKey,
                                           @Query("page") int page);
}
