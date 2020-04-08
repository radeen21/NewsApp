package com.example.data.news.repository.source.network;


import com.example.data.BuildConfig;
import com.example.data.network.BaseNetwork;
import com.example.data.news.NewsResponse;
import com.example.data.news.SourceEntity;
import com.example.data.news.repository.source.NewsEntityData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NetworkNewsEntityData extends BaseNetwork<NewsApi> implements NewsEntityData {

    @Override
    protected Class<NewsApi> getNetworkClass() {
        return NewsApi.class;
    }

    @Override
    protected String getBaseUrl() {
        return "https://newsapi.org";
    }

    @Override
    public Observable<List<SourceEntity>> getSources() {
        return getNetworkService().getNewsSource(BuildConfig.NewsApiKey)
                .subscribeOn(Schedulers.io())
                .map(newsResponse -> newsResponse.sources);
    }

    @Override
    public Observable<NewsResponse> getArticles(String source, int page) {
        return getNetworkService().getNewsArticle(source, BuildConfig.NewsApiKey, page)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<NewsResponse> searchArticle(String keyword, int page) {
        return getNetworkService().searchArticle(keyword, BuildConfig.NewsApiKey, page)
                .subscribeOn(Schedulers.io());
    }
}
