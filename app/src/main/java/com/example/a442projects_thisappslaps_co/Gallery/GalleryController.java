package com.example.a442projects_thisappslaps_co.Gallery;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.a442projects_thisappslaps_co.Database.DatabaseCursorWrapper;
import com.example.a442projects_thisappslaps_co.Database.ProjectDatabaseHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.GalleryTable.Cols.TIMESTAMP;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.GalleryTable.NAME;

class GalleryController {

    private SQLiteDatabase mSQLiteDatabase;

    GalleryController(Context context) {
        mSQLiteDatabase = new ProjectDatabaseHelper(context.getApplicationContext()).getWritableDatabase();
    }

    ArrayList<Project> getProjectList() {
        ArrayList<Project> projects = new ArrayList<>();

        try (DatabaseCursorWrapper cursorWrapper = queryProjects()) {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                projects.add(cursorWrapper.getProject());
                cursorWrapper.moveToNext();
            }
        }

        Collections.reverse(projects);

        return projects;
    }

    private DatabaseCursorWrapper queryProjects() {
        Cursor cursor = mSQLiteDatabase.query(NAME, null, null, null, null, null, null);
        return new DatabaseCursorWrapper(cursor);
    }

    void deleteProject(Project project) {
        if (mSQLiteDatabase.delete(NAME, TIMESTAMP + "=" + project.getTimestamp(), null) > 0) {
            File projectFile = new File(project.getUri());
            if (projectFile.delete()) {
                Log.d("Gallery", "File Deleted");
            }
        }
    }
}
