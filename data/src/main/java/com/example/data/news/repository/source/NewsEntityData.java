package com.example.data.news.repository.source;

import com.example.data.news.NewsResponse;
import com.example.data.news.SourceEntity;

import java.util.List;

import io.reactivex.Observable;

public interface NewsEntityData {

    Observable<List<SourceEntity>> getSources();

    Observable<NewsResponse> getArticles(String domain, int page);

    Observable<NewsResponse> searchArticle(String keyword, int page);

}
