package com.example.domain.news.interactor;

import com.example.domain.UseCase;
import com.example.domain.news.Source;
import com.example.domain.news.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetSourcesUseCase extends UseCase<List<Source>, Void> {

    private NewsRepository newsRepository;

    @Inject
    public GetSourcesUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    protected Observable<List<Source>> buildUseCaseObservable(Void aVoid) {
        return newsRepository.getSources();
    }
}
