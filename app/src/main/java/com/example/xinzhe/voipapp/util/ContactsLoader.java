package com.example.xinzhe.voipapp.util;

import android.content.Context;

import com.example.xinzhe.voipapp.database.VoipDataBase;
import com.example.xinzhe.voipapp.model.Contact;

import java.util.logging.Handler;

/**
 * Created by Xinzhe on 2016/1/19.
 */
public class ContactsLoader extends Thread{
   String secOrder;
    //Context context;
    String[] contactsArray;
    VoipDataBase voipDataBase;
    public ContactsLoader(Context context,String secOrder){
        this.secOrder=secOrder;
       // this.context=context;
        voipDataBase=VoipDataBase.getInstance(context);
    }

    @Override
    public void run() {
        super.run();
       try{

           contactsArray=secOrder.split("%");
            for(String contactString:contactsArray){
                String[] temp=contactString.split("#");
                //first is Id,second is name
                Contact contact=new Contact();
                contact.setContact_id(temp[0]);
                contact.setName(temp[1]);
                voipDataBase.saveContacts(contact);
            }
       }  catch (Exception e){
           e.printStackTrace();
       }
    }

}
