package com.mangesh.newsapp.base;

public interface BasePresenter<V> {

    void attach(V baseView);

    void detach();

}
