package com.example.a442projects_thisappslaps_co.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.a442projects_thisappslaps_co.Explore.Article;
import com.example.a442projects_thisappslaps_co.Gallery.Project;

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
        String url = getString(getColumnIndex(DatabaseSchema.ArticleTable.Cols.URI));
        String title = getString(getColumnIndex(DatabaseSchema.ArticleTable.Cols.TITLE));
        int thumbnail = getInt(getColumnIndex(DatabaseSchema.ArticleTable.Cols.THUMBNAIL));

        Article article = new Article();
        article.setThumbnail(thumbnail);
        article.setUrl(url);
        article.setName(title);

        return article;
    }

}
