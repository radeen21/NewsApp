package com.example.domain.news.interactor;

import com.example.domain.UseCase;
import com.example.domain.news.Article;
import com.example.domain.news.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchArticleUseCase extends UseCase<List<Article>, SearchArticleUseCase.Param> {

    private NewsRepository newsRepository;

    @Inject
    public SearchArticleUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    protected Observable<List<Article>> buildUseCaseObservable(Param param) {
        return newsRepository.searchArticle(param.keyword, param.page);
    }

    public static class Param {

        private String keyword;
        private int page;

        private Param(String keyword, int page) {
            this.keyword = keyword;
            this.page = page;
        }

        public static Param setParams(String keyword, int page) {
            return new Param(keyword, page);
        }
    }
}
