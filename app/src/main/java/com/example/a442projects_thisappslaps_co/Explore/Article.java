package com.example.a442projects_thisappslaps_co.Explore;

import java.util.UUID;

public class Article {

    private String mUUID;
    private String mName;
    private String mUrl;
    private int mThumbnail;
    private boolean mIsFavorited;

    public Article() { }

    public Article(String uuid, String name, String url, int thumbnail, boolean isFavorited) {
        mUUID = uuid;
        mName = name;
        mThumbnail = thumbnail;
        mUrl = url;
        mIsFavorited = isFavorited;
    }

    public String getUUID() {
        return mUUID;
    }

    public void setUUID(String uuid) {
        mUUID = uuid;
    }

    public String getName(){
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.mThumbnail = thumbnail;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public boolean isFavorited() {
        return mIsFavorited;
    }

    public void setFavorited(boolean favorited) {
        mIsFavorited = favorited;
    }
}