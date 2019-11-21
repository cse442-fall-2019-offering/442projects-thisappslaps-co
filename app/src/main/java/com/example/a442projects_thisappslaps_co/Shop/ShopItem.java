package com.example.a442projects_thisappslaps_co.Shop;

public class ShopItem {

    private String mResourceId;
    private String mUUID;
    private String mUrl;
    private String mTitle;
    private String mDescription;
    private boolean mIsAddedToCart;

    public ShopItem(
            String uuid,
            String resourceName,
            String url,
            String title,
            String description,
            boolean isAddedToCart) {

        mResourceId = resourceName;
        mUUID = uuid;
        mUrl = url;
        mTitle = title;
        mDescription = description;
        mIsAddedToCart = isAddedToCart;
    }

    public String getResourceName() {
        return mResourceId;
    }

    public String getUUID() {
        return mUUID;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setIsAddedToCart(boolean isAddedToCart) {
        mIsAddedToCart = isAddedToCart;
    }

    public boolean isAddedToCart() {
        return mIsAddedToCart;
    }
}
