package com.mangesh.newsapp;

import com.mangesh.newsapp.base.BasePresenter;
import com.mangesh.newsapp.data.News;

import java.util.List;

public interface MainActivityContract {

    interface MainActivityView{

    }

    interface MainActivityPresenter extends BasePresenter<MainActivityView>{

        void getNewsList();
    }
}
