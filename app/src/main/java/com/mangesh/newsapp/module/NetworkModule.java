package com.mangesh.newsapp.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mangesh.newsapp.BuildConfig;
import com.mangesh.newsapp.data.ApiRef;
import com.mangesh.newsapp.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
     Retrofit getRetrofit() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/top-headlines/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    ApiRef provideApiRef(Retrofit retrofit){
        return retrofit.create(ApiRef.class);
    }
}
