package com.example.domain.news.interactor;

import com.example.domain.UseCase;
import com.example.domain.news.repository.NewsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetTotalResultUseCase extends UseCase<Integer, Void> {

    private NewsRepository newsRepository;

    @Inject
    public GetTotalResultUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    protected Observable<Integer> buildUseCaseObservable(Void aVoid) {
        return newsRepository.getTotalResult();
    }
}
