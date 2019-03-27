package com.mangesh.newsapp.data;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;


public interface ApiRef {

    @GET("?country=in&apiKey=fc4e5fbe4d2b48098ae4a6c6f2b46876")
    Observable<ResponseBody> getNewsData();
}
