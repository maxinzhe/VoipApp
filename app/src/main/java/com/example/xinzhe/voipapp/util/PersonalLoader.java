package com.example.xinzhe.voipapp.util;

import android.content.SharedPreferences;

/**
 * Created by Xinzhe on 2016/1/19.
 */
public class PersonalLoader extends Thread {
    SharedPreferences.Editor editor;
    String items;
    public PersonalLoader(SharedPreferences.Editor editor,String items) {
        this.editor=editor;
        this.items=items;
        //personal$id%name%balance
    }

    @Override
    public void run() {
        super.run();
        try{
            String [] itemArray=items.split("%");
            editor.putString("id",itemArray[0]);
            editor.putString("name",itemArray[1]);
            editor.putString("balance",itemArray[2]);
            editor.commit();///

        }catch (Exception e){

        }
    }
}
