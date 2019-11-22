package com.example.a442projects_thisappslaps_co.Database;

public class DatabaseSchema {
    public static final class GalleryTable {
        public static final String NAME = "Project";

        public static final class Cols {
            public static final String TIMESTAMP = "timestamp";
            public static final String URI = "uri";
        }
    }

    public static final class ArticleTable {
        public static final String NAME = "Article";

        public static final class Cols {
            public static final String ID = "id";
            public static final String TITLE = "title";
            public static final String THUMBNAIL = "thumbnail";
            public static final String URI = "uri";
            public static final String FAVORITED = "favorited";

        }

    }

    public static final class ShopItemTable {
        public static final String NAME = "ShopItem";

        public static final class Cols {
            public static final String ID = "id";
            public static final String RESOURCE_NAME = "resource_name";
            public static final String URL = "url";
            public static final String TITLE = "title";
            public static final String DESCRIPTION = "description";
            public static final String ADDED_TO_CART = "added_to_cart";
        }
    }
}
