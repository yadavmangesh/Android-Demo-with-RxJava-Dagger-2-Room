package com.mangesh.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mangesh.newsapp.data.News;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView(getIntent());
    }

    private void initView(Intent intent) {

        TextView tvTitle = findViewById(R.id.tvNewTitle);
        TextView tvContent = findViewById(R.id.tvContent);
        TextView tvAuthor = findViewById(R.id.tvAuthor);
        ImageView ivImage = findViewById(R.id.ivImage);

        if (intent.hasExtra("news")){

            News news = intent.getParcelableExtra("news");
            tvAuthor.setText("Author: "+news.getAuthor());
            tvContent.setText(news.getContent());
            tvTitle.setText(news.getTitle());
            Glide.with(this).load(news.getUrlToImage()).placeholder(R.drawable.ic_image).into(ivImage);

        }
    }
}
