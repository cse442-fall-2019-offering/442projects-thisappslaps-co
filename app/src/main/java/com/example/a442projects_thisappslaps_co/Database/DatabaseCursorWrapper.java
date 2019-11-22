package com.example.a442projects_thisappslaps_co.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.a442projects_thisappslaps_co.Explore.Article;
import com.example.a442projects_thisappslaps_co.Gallery.Project;
import com.example.a442projects_thisappslaps_co.Shop.ShopItem;

public class DatabaseCursorWrapper extends CursorWrapper {

    public DatabaseCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Project getProject() {
        String uri = getString(getColumnIndex(DatabaseSchema.GalleryTable.Cols.URI));
        long timestamp = getLong(getColumnIndex(DatabaseSchema.GalleryTable.Cols.TIMESTAMP));

        return new Project(uri, timestamp);
    }

    public Article getArticle(){
        String id =  getString(getColumnIndex(DatabaseSchema.ArticleTable.Cols.ID));
        String url = getString(getColumnIndex(DatabaseSchema.ArticleTable.Cols.URI));
        String title = getString(getColumnIndex(DatabaseSchema.ArticleTable.Cols.TITLE));
        int thumbnail = getInt(getColumnIndex(DatabaseSchema.ArticleTable.Cols.THUMBNAIL));
        int favorited = getInt(getColumnIndex(DatabaseSchema.ArticleTable.Cols.FAVORITED));

        Article article = new Article();
        article.setUUID(id);
        article.setThumbnail(thumbnail);
        article.setUrl(url);
        article.setName(title);
        article.setFavorited(favorited == 1);

        return article;
    }

    public ShopItem getShopItem() {
        String uuid = getString(getColumnIndex(DatabaseSchema.ShopItemTable.Cols.ID));
        String resourceName = getString(getColumnIndex(DatabaseSchema.ShopItemTable.Cols.RESOURCE_NAME));
        String url = getString(getColumnIndex(DatabaseSchema.ShopItemTable.Cols.URL));
        String title = getString(getColumnIndex(DatabaseSchema.ShopItemTable.Cols.TITLE));
        String description = getString(getColumnIndex(DatabaseSchema.ShopItemTable.Cols.DESCRIPTION));
        int addedToCart = getInt(getColumnIndex(DatabaseSchema.ShopItemTable.Cols.ADDED_TO_CART));

        return new ShopItem(uuid,resourceName, url, title, description, addedToCart == 1);
    }
}
