package com.example.newsapp.di.module;

import android.content.Context;

import com.example.data.news.repository.NewsEntityRepository;
import com.example.domain.news.repository.NewsRepository;
import com.example.newsapp.NewsApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final NewsApplication application;

    public ApplicationModule(NewsApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    NewsRepository providesNewsRepository(NewsEntityRepository newsEntityRepository) {
        return newsEntityRepository;
    }
}
