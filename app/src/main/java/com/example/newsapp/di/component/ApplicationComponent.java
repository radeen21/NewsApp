package com.example.newsapp.di.component;

import android.content.Context;

import com.example.domain.news.repository.NewsRepository;
import com.example.newsapp.base.BaseActivity;
import com.example.newsapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    Context context();

    NewsRepository newsRepository();
}
