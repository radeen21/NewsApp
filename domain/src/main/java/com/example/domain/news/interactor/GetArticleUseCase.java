package com.example.domain.news.interactor;

import com.example.domain.UseCase;
import com.example.domain.news.Article;
import com.example.domain.news.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class GetArticleUseCase extends UseCase<List<Article>, GetArticleUseCase.Param> {

    private NewsRepository newsRepository;

    @Inject
    public GetArticleUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    protected Observable<List<Article>> buildUseCaseObservable(Param param) {
        return newsRepository.getArticles(param.source, param.page);
    }

    public static class Param {

        private String source;
        private int page;

        private Param(String domain, int page) {
            this.source = domain;
            this.page = page;
        }

        public static Param setParams(String source, int page) {
            return new Param(source, page);
        }
    }
}
