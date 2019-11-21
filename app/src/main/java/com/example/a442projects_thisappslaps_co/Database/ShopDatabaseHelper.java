package com.example.a442projects_thisappslaps_co.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ShopDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shopdatabase.db";

    public ShopDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "
                + DatabaseSchema.ShopItemTable.NAME +  "("
                + "_id integer primary key autoincrement, "
                + DatabaseSchema.ShopItemTable.Cols.ID + ", "
                + DatabaseSchema.ShopItemTable.Cols.RESOURCE_NAME + ", "
                + DatabaseSchema.ShopItemTable.Cols.TITLE + ", "
                + DatabaseSchema.ShopItemTable.Cols.URL + ", "
                + DatabaseSchema.ShopItemTable.Cols.DESCRIPTION + ", "
                + DatabaseSchema.ShopItemTable.Cols.ADDED_TO_CART + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
}
