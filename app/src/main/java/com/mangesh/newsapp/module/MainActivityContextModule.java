package com.mangesh.newsapp.module;

import android.content.Context;

import com.mangesh.newsapp.MainActivity;
import com.mangesh.newsapp.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {
    private MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    public Context provideContext() {
        return context;
    }

}