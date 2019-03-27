package com.mangesh.newsapp.module;

import com.mangesh.newsapp.MainActivityContract;
import com.mangesh.newsapp.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityMvpModule {
    private final MainActivityContract.MainActivityView mView;


    public MainActivityMvpModule(MainActivityContract.MainActivityView mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    MainActivityContract.MainActivityView provideView() {
        return mView;
    }


}
