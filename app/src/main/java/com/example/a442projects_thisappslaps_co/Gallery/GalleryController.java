package com.example.a442projects_thisappslaps_co.Gallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a442projects_thisappslaps_co.Gallery.Database.DatabaseCursorWrapper;
import com.example.a442projects_thisappslaps_co.Gallery.Database.DatabaseHelper;
import com.example.a442projects_thisappslaps_co.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.a442projects_thisappslaps_co.Gallery.Database.DatabaseSchema.GalleryTable.Cols.TIMESTAMP;
import static com.example.a442projects_thisappslaps_co.Gallery.Database.DatabaseSchema.GalleryTable.Cols.URI;
import static com.example.a442projects_thisappslaps_co.Gallery.Database.DatabaseSchema.GalleryTable.NAME;

class GalleryController {

    private SQLiteDatabase mSQLiteDatabase;

    GalleryController(Context context) {
        mSQLiteDatabase = new DatabaseHelper(context.getApplicationContext()).getWritableDatabase();
    }

    List<Integer> createDummyList() {
        List<Integer> dummyList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            if (i % 5 == 0) {
                dummyList.add(R.color.colorPrimaryDark);
            }
            else if (i % 3 == 0) {
                dummyList.add(R.color.colorWhite);
            }
            else if (i % 2 == 0) {
                dummyList.add(R.color.design_default_color_primary);
            }
            else {
                dummyList.add(R.color.colorAccent);
            }
        }

        return dummyList;
    }

    private ArrayList<Project> getProjects() {
        ArrayList<Project> projects = new ArrayList<>();

        try (DatabaseCursorWrapper cursorWrapper = queryProjects()) {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                projects.add(cursorWrapper.getProject());
                cursorWrapper.moveToNext();
            }
        }

        return projects;
    }

    private void addProjectToDatabase(Project project) {
        mSQLiteDatabase.insert(NAME, null, getContentValues(project));
    }

    private DatabaseCursorWrapper queryProjects() {
        Cursor cursor = mSQLiteDatabase.query(NAME, null, null, null, null, null, null);
        return new DatabaseCursorWrapper(cursor);
    }

    private ContentValues getContentValues(Project project) {
        ContentValues values = new ContentValues();
        values.put(URI, project.getUri());
        values.put(TIMESTAMP, project.getTimestamp());
        return values;
    }
}
