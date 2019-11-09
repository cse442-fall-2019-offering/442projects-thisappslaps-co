package com.example.a442projects_thisappslaps_co.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "
                + DatabaseSchema.GalleryTable.NAME +  "("
                + "_id integer primary key autoincrement, "
                + DatabaseSchema.GalleryTable.Cols.TIMESTAMP + ", "
                + DatabaseSchema.GalleryTable.Cols.URI + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
}
