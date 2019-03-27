package com.mangesh.newsapp.Component;

import android.content.Context;

import com.mangesh.newsapp.MyApplication;
import com.mangesh.newsapp.data.ApiRef;
import com.mangesh.newsapp.module.ContextModule;
import com.mangesh.newsapp.module.NetworkModule;
import com.mangesh.newsapp.qualifier.ApplicationContext;
import com.mangesh.newsapp.scope.ApplicationScope;

import dagger.Component;

@Component(modules = {ContextModule.class,NetworkModule.class   })
@ApplicationScope
public interface ApplicationComponent {


    ApiRef getApiRef();


    @ApplicationContext
    Context getContext();


    void injectApplication(MyApplication myApplication);
}
