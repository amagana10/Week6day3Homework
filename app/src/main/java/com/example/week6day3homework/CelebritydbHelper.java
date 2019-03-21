package com.example.week6day3homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.example.week6day3homework.ProviderContract.*;

public class CelebritydbHelper extends SQLiteOpenHelper {
    public CelebritydbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createCelebTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private void createCelebTable(SQLiteDatabase db) {
        String createGenreTableQuery = "CREATE TABLE " + TABLE_NAME_CELEBRITY + " ("
                + COLUMN_NAME + " TEXT, "+ COLUMN_IMG + " TEXT)";

        db.execSQL(createGenreTableQuery);

    }

    public long insertCeleb(Celebrity celebrity) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, celebrity.getName());
        contentValues.put(COLUMN_IMG, celebrity.getImage());

        return database.insert(TABLE_NAME_CELEBRITY,null, contentValues);
    }

    public long updateCelebs(Celebrity celebrity) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String whereClause = " WHERE " + COLUMN_NAME + " = \"" + celebrity.getName() + "\"";

        contentValues.put(COLUMN_NAME, celebrity.getName());
        contentValues.put(COLUMN_IMG, celebrity.getImage());

        return database.update(TABLE_NAME_CELEBRITY, contentValues, whereClause, null);
    }

    public long deleteCeleb(String celebrityName) {
        SQLiteDatabase database = this.getWritableDatabase();

        return database.delete(TABLE_NAME_CELEBRITY,
                " WHERE " + COLUMN_NAME + " = \"" + celebrityName + "\""
                ,null);
    }

    public ArrayList<Celebrity> retriveAllCelebs() {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectionQuery = "SELECT * FROM " + TABLE_NAME_CELEBRITY;
        ArrayList<Celebrity> celebrities = new ArrayList<>();

        Cursor cursor = database.rawQuery(selectionQuery, null);

        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String image = cursor.getString(cursor.getColumnIndex(COLUMN_IMG));

                celebrities.add(new Celebrity(name,image));
            } while(cursor.moveToNext());
        }

        cursor.close();
        return celebrities;
    }



}
