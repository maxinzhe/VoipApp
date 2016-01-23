package com.example.xinzhe.voipapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.net.Socket;

/**
 * Created by Xinzhe on 2016/1/18.
 */
public class LoginInfoLoader extends Thread {
    Socket socket;
    String topOrder;
    String topOrder2;
    Context context;
    @Override
    public void run() {
        super.run();

    }

     LoginInfoLoader(Socket socket,Context context){
        this.socket=socket;
        topOrder=new SocketHelper(socket).getInputString();
         //topOrder2=new SocketHelper(socket).getInputString();
         SharedPreferences sharedPreferences= context.getSharedPreferences("personal", Activity.MODE_PRIVATE);
         SharedPreferences.Editor editor=sharedPreferences.edit();
        try{
          //  new SocketHelper(socket).outPutString("contacts");
           // topOrder=new  SocketHelper(socket).getInputString();
            String[] secOrder=topOrder.split("\\$");
            if(secOrder==null||secOrder.length<2){
                throw new Exception();
            }
            switch(secOrder[0]){
                case "contacts":
                    new ContactsLoader(context,secOrder[1]).start();
                    break;
                case"personal"://personal$id%name%balance
                    new PersonalLoader(editor,secOrder[1]).start();
                    break;
                case"certificate":

                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
