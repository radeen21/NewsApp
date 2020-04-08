package com.example.newsapp.di.component;

import com.example.newsapp.article.ArticleActivity;
import com.example.newsapp.di.PerActivity;
import com.example.newsapp.di.module.ActivityModule;
import com.example.newsapp.di.module.NewsModule;
import com.example.newsapp.source.SourceActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, NewsModule.class})
public interface NewsComponent {

    void inject(SourceActivity sourceActivity);
    void inject(ArticleActivity articleActivity);
}
