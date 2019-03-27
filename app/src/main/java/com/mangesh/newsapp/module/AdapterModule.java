package com.mangesh.newsapp.module;

import android.content.Context;

import com.mangesh.newsapp.MainActivity;
import com.mangesh.newsapp.MainAdapter;
import com.mangesh.newsapp.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public MainAdapter getNewsList(Context context,MainAdapter.ClickListener clickListener){
        return new MainAdapter(context,clickListener);
    }

    @Provides
    @ActivityScope
    public MainAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }
}
