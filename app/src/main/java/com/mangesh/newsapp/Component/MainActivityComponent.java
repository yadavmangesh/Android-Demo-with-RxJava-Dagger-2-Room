package com.mangesh.newsapp.Component;

import com.mangesh.newsapp.MainActivity;
import com.mangesh.newsapp.module.AdapterModule;
import com.mangesh.newsapp.module.MainActivityMvpModule;
import com.mangesh.newsapp.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = {AdapterModule.class, MainActivityMvpModule.class},
        dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

}
