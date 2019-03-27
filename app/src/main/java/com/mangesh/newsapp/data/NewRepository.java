package com.mangesh.newsapp.data;

import java.util.List;
import io.reactivex.Maybe;
import io.reactivex.Single;


public class NewRepository {

    private NewsDao newsDao;

    public NewRepository(NewsDao newsDao){
        this.newsDao=newsDao;
    }

    public Single<List<News>> getAllNews(){
       return newsDao.getNewsList();
    }

    public void insertNews(List<News> list){
        newsDao.insertNews(list);
    }
}
