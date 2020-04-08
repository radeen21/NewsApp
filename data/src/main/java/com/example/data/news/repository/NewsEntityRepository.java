package com.example.data.news.repository;

import android.content.Context;

import com.example.data.Preference;
import com.example.data.news.mapper.ArticleEntityMapper;
import com.example.data.news.mapper.SourceEntityMapper;
import com.example.data.news.repository.source.NewsEntityDataFactory;
import com.example.domain.news.Article;
import com.example.domain.news.Source;
import com.example.domain.news.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class NewsEntityRepository implements NewsRepository {

    private final Context context;

    private final String totalResultPrefKey = "pref_total_result";

    private final NewsEntityDataFactory newsEntityDataFactory;

    private final SourceEntityMapper sourceEntityMapper;

    private final ArticleEntityMapper articleEntityMapper;

    @Inject
    public NewsEntityRepository(Context context, NewsEntityDataFactory newsEntityDataFactory,
                                SourceEntityMapper sourceEntityMapper,
                                ArticleEntityMapper articleEntityMapper) {
        this.context = context;
        this.newsEntityDataFactory = newsEntityDataFactory;
        this.sourceEntityMapper = sourceEntityMapper;
        this.articleEntityMapper = articleEntityMapper;
    }

    @Override
    public Observable<List<Source>> getSources() {
        return newsEntityDataFactory.createData().getSources()
                .map(sourceEntityMapper::transform)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Article>> getArticles(String source, int page) {
        return newsEntityDataFactory.createData().getArticles(source, page)
                .doOnNext(newsResponse -> Preference
                        .getInstance(context).put(totalResultPrefKey, newsResponse.totalResults))
                .map(newsResponse -> articleEntityMapper.transform(newsResponse.articles))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Article>> searchArticle(String keyword, int page) {
        return newsEntityDataFactory.createData().searchArticle(keyword, page)
                .doOnNext(newsResponse -> Preference
                        .getInstance(context).put(totalResultPrefKey, newsResponse.totalResults))
                .map(newsResponse -> articleEntityMapper.transform(newsResponse.articles))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Integer> getTotalResult() {
        return Observable.just(Preference.getInstance(context).getInt(totalResultPrefKey));
    }
}
