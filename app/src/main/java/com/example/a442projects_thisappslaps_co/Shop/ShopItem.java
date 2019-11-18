package com.example.a442projects_thisappslaps_co.Shop;

public class ShopItem {

    private Integer mResourceId;
    private String mUrl;

    public ShopItem(Integer resourceId, String url) {
        mResourceId = resourceId;
        mUrl = url;
    }

    public Integer getResourceId() {
        return mResourceId;
    }

    public String getUrl() {
        return mUrl;
    }
}
