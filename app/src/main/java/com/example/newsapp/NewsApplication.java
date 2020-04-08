package com.example.newsapp;

import android.app.Application;

import com.example.newsapp.di.component.ApplicationComponent;
import com.example.newsapp.di.component.DaggerApplicationComponent;
import com.example.newsapp.di.module.ApplicationModule;

public class NewsApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initInjector();
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
