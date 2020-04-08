package com.example.newsapp.di.module;

import android.app.Activity;

import com.example.newsapp.di.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    Activity providesActivity() {
        return activity;
    }
}
