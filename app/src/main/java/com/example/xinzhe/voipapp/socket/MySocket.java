package com.example.xinzhe.voipapp.socket;

import java.net.Socket;

/**
 * Created by Xinzhe on 2016/1/23.
 */
public class MySocket {
    Socket socket;

    public MySocket(){
        try{
            Socket socket = new Socket("192.168.253.1",8000);
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public Socket getSocket(){
       return socket;
    }


}
