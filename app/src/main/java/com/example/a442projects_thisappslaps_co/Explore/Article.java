package com.example.a442projects_thisappslaps_co.Explore;

public class Article {
    private String name;
    private int thumbnail;
    private String url;

    public Article() {

    }

    public Article(String name, String url, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.url = url;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}