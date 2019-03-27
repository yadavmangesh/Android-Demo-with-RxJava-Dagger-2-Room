package com.mangesh.newsapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.mangesh.newsapp.Component.ApplicationComponent;
import com.mangesh.newsapp.Component.DaggerApplicationComponent;
import com.mangesh.newsapp.module.ContextModule;


public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent= DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);
    }
    public static MyApplication get(Activity activity){
        context= activity.getApplication();
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
