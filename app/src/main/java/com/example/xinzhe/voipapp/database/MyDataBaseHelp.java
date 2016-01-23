package com.example.xinzhe.voipapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Xinzhe on 2016/1/19.
 */
public class MyDataBaseHelp extends SQLiteOpenHelper {
private static final String CREATE_CONTACTS="create table Contacts(" +
            "id integer primary key autoincrement," +
            "name text," +
            "contact_id text" +
            ")";
    private static final String CLEAR_CONTACTS="drop table Contacts";
    private static final String CREATE_ONLINE="create table Online(" +
            "id integer primary key autoincrement," +
            "onlineid text" +
            ")" ;
    public MyDataBaseHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL(CLEAR_DB);
        db.execSQL(CREATE_CONTACTS);
        db.execSQL(CREATE_ONLINE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
