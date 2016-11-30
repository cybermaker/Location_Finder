package com.example.toshiba.location_finder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by toshiba on 11/27/2016.
 */
public class Databasehelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDB";
    private static final String TABLE_NAME = "Userlist";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGTITUDE = "longtitude";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table Userlist (id integer primary key not null, username text not null, password text not null, latitude double not null, longtitude);";

    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXIST " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void Insertuser(Userdata user) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from Userlist";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        values.put(COLUMN_ID, count);
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_LATITUDE, user.getLatitude());
        values.put(COLUMN_LONGTITUDE, user.getLongtitude());
        db.insert(TABLE_NAME, null, values);
    }

    public String searchpass(String user) {
        db = this.getReadableDatabase();
        String query = "select username, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String userc;
        String pass;
        pass = "not found";
        if (cursor.moveToFirst()) {
            do {
                userc = cursor.getString(0);
                if (userc.equals(user)) {
                    pass = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return pass;
    }
    public Cursor readallusername(){
        db = this.getReadableDatabase();
        String uquery = "select username from " + TABLE_NAME;
        Cursor ucursor = db.rawQuery(uquery,null);
        return ucursor;
    }
    public double searchlat(String user) {
        db = this.getReadableDatabase();
        String query = "select username, password, latitude, longtitude from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String userc;
        double longt;
        double lat;
        lat = 0; longt =0;
        if (cursor.moveToFirst()) {
            do {
                userc = cursor.getString(0);
                if (userc.equals(user)) {
                    lat = Double.parseDouble(cursor.getString(2));
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return lat;

    }
    public double searchlong(String user) {
        db = this.getReadableDatabase();
        String query = "select username, password, latitude, longtitude from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String userc;
        double longt;
        longt = 0;
        if (cursor.moveToFirst()) {
            do {
                userc = cursor.getString(0);
                if (userc.equals(user)) {
                    longt = Double.parseDouble(cursor.getString(3));
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return longt;

    }

}
