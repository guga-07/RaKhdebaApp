package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



 class DB extends SQLiteOpenHelper {
     public Context context;
     public static final String DATABASE_NAME = "info.db";
     public static final int DATABASE_VERSION = 1;

     public static final String TABLE_NAME = "my_info";
     public static final String COLUMN_ID = "_id";
     public static final String COLUMN_INFO = "Info";

     public DB(@Nullable Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
         this.context = context;
     }

     @Override
     public void onCreate(SQLiteDatabase db) {
         String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COLUMN_INFO + " TEXT );";
         db.execSQL(query);


     }

     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
         onCreate(db);

     }

     public void addinfo(String INFO) {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(COLUMN_INFO, INFO);
         long result = db.insert(TABLE_NAME, null, cv);

     }


     Cursor readAllData() {
         String query = "SELECT * FROM " + TABLE_NAME;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = null;
        if (db != null) {
             cursor = db.rawQuery(query, null);

         }

         return cursor;
     }


     /* void deleteAllData(long delete) {
         SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("DELETE FROM " + TABLE_NAME  + " WHERE " + COLUMN_ID   + " = " + delete );

       */


     void deleteData(long idToDelete) {
         SQLiteDatabase db = this.getWritableDatabase();
         db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(idToDelete)});
         db.close();


     }

     public long getPrimaryKeyFromSQLite(String itemName) {

         long primaryKey = -1;
         SQLiteDatabase db = this.getWritableDatabase();
         String query = "SELECT * FROM " + TABLE_NAME;
         Cursor cursor = db.rawQuery(query,null);
         if (cursor.moveToFirst()) {
             primaryKey = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
         }

         cursor.close();
         return primaryKey;
     }


 }