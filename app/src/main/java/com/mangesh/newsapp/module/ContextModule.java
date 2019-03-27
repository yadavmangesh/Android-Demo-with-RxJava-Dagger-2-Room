package com.mangesh.newsapp.module;

import android.content.Context;

import com.mangesh.newsapp.qualifier.ApplicationContext;
import com.mangesh.newsapp.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
