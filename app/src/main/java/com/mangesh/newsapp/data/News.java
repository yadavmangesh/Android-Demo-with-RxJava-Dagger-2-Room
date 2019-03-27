package com.mangesh.newsapp.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class News implements Parcelable {


    public News(){

    }
@SerializedName("source")
@Expose
@Embedded
private Source source;
@SerializedName("author")
@Expose
private String author;
@SerializedName("title")
@Expose
private String title;
@SerializedName("description")
@Expose
private String description;
@SerializedName("url")
@Expose
private String url;
@SerializedName("urlToImage")
@Expose
private String urlToImage;
@SerializedName("publishedAt")
@Expose
@PrimaryKey
@NonNull
private String publishedAt;
@SerializedName("content")
@Expose
private String content;

    protected News(Parcel in) {
        author = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();
        content = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public Source getSource() {
return source;
}

public void setSource(Source source) {
this.source = source;
}

public String getAuthor() {
return author;
}

public void setAuthor(String author) {
this.author = author;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public String getUrlToImage() {
return urlToImage;
}

public void setUrlToImage(String urlToImage) {
this.urlToImage = urlToImage;
}

public String getPublishedAt() {
return publishedAt;
}

public void setPublishedAt(String publishedAt) {
this.publishedAt = publishedAt;
}

public String getContent() {
return content;
}

public void setContent(String content) {
this.content = content;
}

    @Override
    public String toString() {
        return "News{" +
                "source=" + source +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(urlToImage);
        dest.writeString(publishedAt);
        dest.writeString(content);
    }
}