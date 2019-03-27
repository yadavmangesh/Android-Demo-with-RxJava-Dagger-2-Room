package com.mangesh.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mangesh.newsapp.data.News;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {

    private List<News> newsList;
    private MainAdapter.ClickListener clickListener;
    private Context context;

    public MainAdapter(Context context,ClickListener clickListener){
        this.clickListener=clickListener;
        newsList=new ArrayList<>();
        this.context=context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_row,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.tvDescription.setText(newsList.get(i).getDescription());
        myHolder.tvTitle.setText(newsList.get(i).getTitle());

        Glide.with(context).load(newsList.get(i)
                .getUrlToImage())
                .placeholder(R.drawable.ic_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myHolder.ivNewsImage);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void setData(List<News> newsList){
         this. newsList.addAll(newsList);
         notifyDataSetChanged();
    }

    public interface ClickListener {
        void startIntent(News news);
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView ivNewsImage;

        private TextView tvTitle;

        private TextView tvDescription;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            ivNewsImage=itemView.findViewById(R.id.ivNew);
            tvTitle=itemView.findViewById(R.id.tvNewTitle);
            tvDescription=itemView.findViewById(R.id.tvNewDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.startIntent(newsList.get(getAdapterPosition()));
                }
            });
        }
    }
}
