package com.example.newsapp.di.component;

import android.app.Activity;

import com.example.newsapp.di.PerActivity;
import com.example.newsapp.di.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
