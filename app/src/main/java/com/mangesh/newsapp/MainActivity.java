package com.mangesh.newsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mangesh.newsapp.Component.ApplicationComponent;
import com.mangesh.newsapp.Component.DaggerMainActivityComponent;
import com.mangesh.newsapp.Component.MainActivityComponent;
import com.mangesh.newsapp.data.NewRepository;
import com.mangesh.newsapp.data.News;
import com.mangesh.newsapp.data.NewsDatabase;
import com.mangesh.newsapp.module.MainActivityContextModule;
import com.mangesh.newsapp.module.MainActivityMvpModule;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainActivityContract.MainActivityView,MainAdapter.ClickListener {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;

    @Inject
    public MainAdapter adapter;

    @Inject
    public MainActivityPresenterImpl mainActivityPresenter;

    MainActivityComponent mainActivityComponent;

    private NewRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        ApplicationComponent applicationComponent=MyApplication.get(this).getApplicationComponent();
        mainActivityComponent= DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();


        mainActivityComponent.injectMainActivity(this);
        mainActivityPresenter.getNewsList();
        try {
            repository=new NewRepository(NewsDatabase.getDatabase(this).newsDao());
        }catch (Exception e){
            e.printStackTrace();
        }

        initView();
    }
    @SuppressLint("CheckResult")
    private void initView(){
        recyclerView=findViewById(R.id.rvnews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        repository.getAllNews().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<News> newsList) {
                      adapter.setData(newsList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }


    @Override
    public void startIntent(News news) {
        Log.d(TAG, "startIntent: "+news.toString());
        Intent intent=new Intent(this,DetailActivity.class);
        intent.putExtra("news",news);
        startActivity(intent);
    }
}
