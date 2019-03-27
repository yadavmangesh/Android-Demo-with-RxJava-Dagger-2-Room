package com.mangesh.newsapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mangesh.newsapp.base.Presenter;
import com.mangesh.newsapp.data.ApiRef;
import com.mangesh.newsapp.data.NewRepository;
import com.mangesh.newsapp.data.News;
import com.mangesh.newsapp.data.NewsDatabase;
import com.mangesh.newsapp.qualifier.ApplicationContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class MainActivityPresenterImpl extends Presenter<MainActivityContract.MainActivityView> implements
        MainActivityContract.MainActivityPresenter {

    private ApiRef apiRef;

    private MainActivityContract.MainActivityView mainActivityView;

    private static final String TAG = "MainActivityPresenterIm";

    private NewRepository newRepository;

    private NewsDatabase newsDatabase;



    @Inject
    public MainActivityPresenterImpl(MainActivityContract.MainActivityView view, ApiRef apiRef) {
        this.apiRef = apiRef;
        mainActivityView = view;
        newsDatabase=NewsDatabase.getDatabase(MyApplication.context);
        newRepository=new NewRepository(newsDatabase.newsDao());
    }

    @Override
    public void getNewsList() {


        apiRef.getNewsData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String result = responseBody.string();
                            Log.d(TAG, "onNext: " + result);
                            JSONObject jsonObject = new JSONObject(result);
                            Log.d(TAG, "jsonObject: " + jsonObject.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("articles");
                            Gson gson = new Gson();

                            Type newsListType = new TypeToken<List<News>>() {
                            }.getType();

                            final List<News> newsList = gson.fromJson(jsonArray.toString(), newsListType);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    newRepository.insertNews(newsList);
                                }
                            }).start();

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
