package com.mangesh.newsapp.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;
import io.reactivex.Single;

@Dao
public interface NewsDao {

    @Query("select * from News")
    Single<List<News>> getNewsList();

   @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(List<News> newsList);

}
