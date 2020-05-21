package com.example.assign3flashcards;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyCardDB.db";
    public static final String CARD_FRONT = "front";
    public static final String CARD_BACK = "back";
    public String CURRENT_TABLE;

    public DBHelper(Context context, String TABLE_NAME) {
        super(context, DATABASE_NAME , null, 1);
        this.CURRENT_TABLE = TABLE_NAME;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + CURRENT_TABLE + " " +
                        "(id integer primary key, front text,back text)"
        );

    }

    public void makeTable(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "create table IF NOT EXISTS " + tableName + " " +
                        "(id integer primary key, front text,back text)"
        );
    }

    public void emptyTable(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + tableName);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CURRENT_TABLE);
        onCreate(db);
    }

    public boolean addCard (String front, String back, String CURRENT_TABLE) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARD_FRONT, front);
        contentValues.put(CARD_BACK, back);
        db.insert(CURRENT_TABLE, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + CURRENT_TABLE + " where id="+id+"", null );
        return res;
    }

    public ArrayList<String> viewCards() {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String SQLQ = "select * from " + CURRENT_TABLE;
        Cursor res =  db.rawQuery( SQLQ, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CARD_FRONT)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> viewById(int id) {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String SQLQ = "select * from " + CURRENT_TABLE + " where id = " + id;
        Cursor res =  db.rawQuery( SQLQ, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CARD_FRONT)));
            array_list.add(res.getString(res.getColumnIndex(CARD_BACK)));
            res.moveToNext();
        }
        return array_list;
    }
}

