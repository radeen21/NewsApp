package com.example.newsapp.di.module;

import com.example.domain.news.interactor.GetSourcesUseCase;
import com.example.domain.news.repository.NewsRepository;
import com.example.newsapp.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    @Provides
    @PerActivity
    @Named("getTvSchedule")
    public GetSourcesUseCase getTvScheduleUseCase(NewsRepository newsRepository) {
        return new GetSourcesUseCase(newsRepository);
    }
}
