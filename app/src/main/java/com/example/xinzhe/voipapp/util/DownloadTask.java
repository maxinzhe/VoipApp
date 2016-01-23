package com.example.xinzhe.voipapp.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.xinzhe.voipapp.activity.LoginActivity;

import java.net.Socket;

/**
 * Created by Xinzhe on 2016/1/19.
 */
public class DownloadTask extends AsyncTask <Void,Integer,Boolean>{
    ProgressDialog progressDialog;
    Socket socket;
    Context context;

    public DownloadTask(Context context,Socket socket) {
        this.progressDialog=ProgressDialog.show(context,"正在加载中","请稍后");
        this.socket=socket;
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        //String receive=new SocketHelper(socket).getInputString();
        new LoginInfoLoader(socket,context).start();//for personal or contacts
        new LoginInfoLoader(socket,context).start();//for personal or contacts
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {


        super.onPostExecute(aBoolean);
        progressDialog.dismiss();
    }
}
