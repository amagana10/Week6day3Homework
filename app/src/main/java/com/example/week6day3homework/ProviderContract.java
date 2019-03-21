package com.example.week6day3homework;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class ProviderContract {
    public static final String CONTENT_AUTHORITY = "com.example.week6day3homework";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //Database base info
    public static final String DATABASE_NAME = "celebrity_db";
    public static final int DATABASE_VERSION = 1;
    //Table names
    public static final String TABLE_NAME_CELEBRITY = "celebrity_table";
    //Columns - Movies
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMG = "image";

    public static final class CelebrityEntry implements BaseColumns {
        // Content URI represents the base location for the table
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME_CELEBRITY).build();

        // These are special type prefixes that specify if a URI returns a list or a specific item
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_URI + "/" + TABLE_NAME_CELEBRITY;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_URI + "/" + TABLE_NAME_CELEBRITY;

        // Define a function to build a URI to find a specific movie by it's identifier
        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

}
