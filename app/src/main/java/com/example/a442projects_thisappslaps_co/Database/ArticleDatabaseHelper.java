package com.example.a442projects_thisappslaps_co.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ArticleDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "articledatabase.db";

    public ArticleDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "
                + DatabaseSchema.ArticleTable.NAME +  "("
                + "_id integer primary key autoincrement, "
                + DatabaseSchema.ArticleTable.Cols.ID + ", "
                + DatabaseSchema.ArticleTable.Cols.THUMBNAIL + ", "
                + DatabaseSchema.ArticleTable.Cols.URI + ", "
                + DatabaseSchema.ArticleTable.Cols.TITLE + ", "
                + DatabaseSchema.ArticleTable.Cols.FAVORITED + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
}
