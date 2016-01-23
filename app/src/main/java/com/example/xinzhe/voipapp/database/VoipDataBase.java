package com.example.xinzhe.voipapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xinzhe.voipapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xinzhe on 2016/1/19.
 */

public class VoipDataBase {
    public final static String  DB_NAME="VoipDB";
    public static final int VERSION=1;
    private static VoipDataBase voipDataBase;
    private SQLiteDatabase db;

    //private constructor


 private VoipDataBase(Context context) {
     MyDataBaseHelp voipDataBaseHelper=new MyDataBaseHelp(context,DB_NAME,null,VERSION);
    db=voipDataBaseHelper.getWritableDatabase();
 }
    public synchronized static VoipDataBase getInstance(Context context){
        if(voipDataBase==null){
            voipDataBase=new VoipDataBase(context);
        }
        return voipDataBase;
    }
    public void clearContactsTable(){//clear the table for new login
        //Cursor cursor=db.query("Contacts",null,null,null,null,null,null);
       // if(cursor.moveToNext()){
            db.execSQL("delete from Contacts");

       // }
    }
    public void saveContacts(Contact contact){
        if(contact!=null){
            ContentValues values=new ContentValues();
            values.put("name",contact.getName());
            values.put("contact_id",contact.getContact_id());
            db.insert("Contacts",null,values);
        }
    }

    public List<Contact> queryContacts(){
        List<Contact> contactsList=new ArrayList<Contact>();
        Cursor cursor=db.query("Contacts",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Contact contact=new Contact();
                contact.setName(cursor.getString(cursor.getColumnIndex("name")));
                contact.setContact_id(cursor.getString(cursor.getColumnIndex("contact_id")));
                contactsList.add(contact);
            }while(cursor.moveToNext());

        }
        return contactsList;
    }
}
