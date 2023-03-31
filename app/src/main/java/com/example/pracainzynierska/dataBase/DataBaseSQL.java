package com.example.pracainzynierska.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pracainzynierska.meeting.Meet;

import java.util.Calendar;

public class DataBaseSQL extends SQLiteOpenHelper {

    public DataBaseSQL(Context context) {
        super(context, "schedules.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Schedules (id integer primary key autoincrement, title text, description text, location text, time long)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addSchedule(Meet meet) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", meet.getMeetTitle());
        values.put("description", meet.getMeetDescription());
        values.put("location", meet.getMeetLocation());
        values.put("time", meet.getCal().getTimeInMillis());
    }
}
