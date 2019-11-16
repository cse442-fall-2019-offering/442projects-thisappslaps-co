package com.example.a442projects_thisappslaps_co.Explore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a442projects_thisappslaps_co.Database.ArticleDatabaseHelper;
import com.example.a442projects_thisappslaps_co.Database.DatabaseCursorWrapper;

import java.util.ArrayList;

import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ArticleTable.Cols.THUMBNAIL;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ArticleTable.Cols.URI;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ArticleTable.Cols.TITLE;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ArticleTable.NAME;

public class ExploreController {

    private SQLiteDatabase mSQLiteDatabase;

    ExploreController(Context context){
        mSQLiteDatabase = new ArticleDatabaseHelper(context.getApplicationContext()).getWritableDatabase();
    }

    ArrayList<Article> getArticleList() {
        ArrayList<Article> articles = new ArrayList<>();

        try (DatabaseCursorWrapper cursorWrapper = queryProjects()) {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                articles.add(cursorWrapper.getArticle());
                cursorWrapper.moveToNext();
            }
        }

        return articles;
    }

    private DatabaseCursorWrapper queryProjects() {
        Cursor cursor = mSQLiteDatabase.query(NAME, null, null, null, null, null, null);
        return new DatabaseCursorWrapper(cursor);
    }

    public void addArticleToDatabase(Article article) {
        mSQLiteDatabase.insert(NAME, null, getContentValues(article));
    }

    private static ContentValues getContentValues(Article article) {
        ContentValues values = new ContentValues();
        values.put(URI, article.getUrl());
        values.put(THUMBNAIL, article.getThumbnail());
        values.put(TITLE, article.getName());
        return values;
    }
}
