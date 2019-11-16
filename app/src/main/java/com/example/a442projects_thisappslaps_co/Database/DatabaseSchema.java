package com.example.a442projects_thisappslaps_co.Database;

public class DatabaseSchema {
    public static final class GalleryTable {
        public static final String NAME = "Project";

        public static final class Cols {
            public static final String TIMESTAMP = "timestamp";
            public static final String URI = "uri";
        }
    }

    public static final class ArticleTable{
        public static final String NAME = "Article";

        public static final class Cols {
            public static final String TITLE = "title";
            public static final String THUMBNAIL = "thumbnail";
            public static final String URI = "uri";
        }
    }
}
