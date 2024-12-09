package com.example.signuploginrealtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Database Name and Version
    private static final String DATABASE_NAME = "elearning.db";
    private static final int DATABASE_VERSION = 1;

    // Table Name and Columns
    public static final String TABLE_COURSES = "courses";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_IMAGE = "image"; // Store as a path or URL
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_VIDEO_YOUTUBE = "video_youtube";

    // SQL Statement to Create the Courses Table
    private static final String CREATE_TABLE_COURSES = "CREATE TABLE " + TABLE_COURSES + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT NOT NULL, "
            + COLUMN_CATEGORY + " TEXT, "
            + COLUMN_IMAGE + " TEXT, "
            + COLUMN_DESCRIPTION + " TEXT, "
            + COLUMN_VIDEO_YOUTUBE + " TEXT);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COURSES); // Create courses table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);
    }

    // Insert a course
    public long insertCourse(String title, String category, String image, String description, String videoYouTube) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_IMAGE, image);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_VIDEO_YOUTUBE, videoYouTube);
        return db.insert(TABLE_COURSES, null, values);
    }

    // Retrieve all courses
    public Cursor getAllCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_COURSES;
        return db.rawQuery(query, null);
    }


    // Update a course
    public int updateCourse(int id, String title, String category, String image, String description, String videoYouTube) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_IMAGE, image);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_VIDEO_YOUTUBE, videoYouTube);
        return db.update(TABLE_COURSES, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    // Delete a course
    public int deleteCourse(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_COURSES, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }
}
