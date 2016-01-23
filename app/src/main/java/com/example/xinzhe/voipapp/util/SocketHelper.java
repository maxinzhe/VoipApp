package com.example.xinzhe.voipapp.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Xinzhe on 2016/1/17.
 */
public class SocketHelper {
    Socket socket;
    String string;
    InputStreamReader isr;
    InputStream is;
    BufferedReader br;

    OutputStream outputStream;
    OutputStreamWriter outputStreamWriter;
    PrintWriter printWriter;
    public String getInputString(){
        try{
             is =socket.getInputStream() ;
             isr = new InputStreamReader(is) ;
             br = new BufferedReader(isr) ;

            string= br.readLine() ;

        }catch (Exception e){
            e.printStackTrace();
        }

        return string;
    }
    public  void outPutString(String outString){
        try{
             outputStream=socket.getOutputStream();
             outputStreamWriter=new OutputStreamWriter(outputStream);
             printWriter=new PrintWriter(outputStreamWriter,true);
            printWriter.println(outString);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public SocketHelper(Socket socket){
       this.socket=socket;
    }
}
