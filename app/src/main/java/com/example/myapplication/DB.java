package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "posts";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_POSTS = "posts";

    // Post Table Columns
    private static final String KEY_POST_ID = "id";
    private static final String KEY_POST_TITLE = "title";
    private static final String KEY_POST_AUTHOR = "author";
    private static final String KEY_POST_TEXT = "content";



    public DB( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POSTS_TABLE = "CREATE TABLE " + TABLE_POSTS +
                "(" +
                KEY_POST_ID + " INTEGER PRIMARY KEY," +
                KEY_POST_TITLE + " TEXT ," +
                KEY_POST_AUTHOR + " TEXT ," +
                KEY_POST_TEXT + " TEXT" +
                ")";
        db.execSQL(CREATE_POSTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
        onCreate(db);
    }

    public void addPost(String title, String author, String content ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_POST_TITLE, title);
        values.put(KEY_POST_AUTHOR, author);
        values.put(KEY_POST_TEXT, content);
        db.insert(TABLE_POSTS, null, values);
        db.close();
    }


    public ArrayList<PostModal> readPosts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorPosts = db.rawQuery("SELECT * FROM " + TABLE_POSTS, null);
        ArrayList<PostModal> PostModalArrayList = new ArrayList<>();
        if (cursorPosts.moveToFirst()) {
            do {
                PostModalArrayList.add(new PostModal(cursorPosts.getString(1),
                        cursorPosts.getString(2),
                        cursorPosts.getString(3)));
            } while (cursorPosts.moveToNext());
        }
        cursorPosts.close();
        return PostModalArrayList;
    }

}


