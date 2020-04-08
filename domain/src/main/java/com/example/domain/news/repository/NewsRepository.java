package com.example.domain.news.repository;

import com.example.domain.news.Article;
import com.example.domain.news.Source;

import java.util.List;

import io.reactivex.Observable;

public interface NewsRepository {

    Observable<List<Source>> getSources();

    Observable<List<Article>> getArticles(String domain, int page);

    Observable<Integer> getTotalResult();

    Observable<List<Article>> searchArticle(String keyword, int page);

}
