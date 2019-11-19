package com.example.a442projects_thisappslaps_co.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

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
}
