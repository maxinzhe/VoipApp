package com.example.xinzhe.voipapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xinzhe.voipapp.R;
import com.example.xinzhe.voipapp.activity.service.MonitorService;
import com.example.xinzhe.voipapp.database.VoipDataBase;
import com.example.xinzhe.voipapp.util.DownloadTask;
import com.example.xinzhe.voipapp.util.SocketHelper;
import com.example.xinzhe.voipapp.util.StringJointer;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 * Created by Xinzhe on 2016/1/13.
 */
public class LoginActivity extends Activity {
    EditText userNameText;
    EditText userPwText;
    Button  loginBtn;
    Socket socket;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    VoipDataBase voipDataBase;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if(msg.what==1){
               voipDataBase=VoipDataBase.getInstance(LoginActivity.this);
               voipDataBase.clearContactsTable();
                //start a new thread to download the contacts,personal and certificate
               new DownloadTask(LoginActivity.this,socket).execute();


                                     new AlertDialog.Builder(LoginActivity.this).setTitle("登录成功")
                       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                               Intent serviceIntent =new Intent(LoginActivity.this, MonitorService.class);
                               startService(serviceIntent);


                               Intent intent=new Intent();
                               intent.setClass(LoginActivity.this,MenuActivity.class);
                               startActivity(intent);


                           }
                       }).show();

           }else if(msg.what==2){
               handler.post(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(LoginActivity.this).setTitle("用户名或密码不正确，请重试")
                                .setPositiveButton("确定",null).show();
                    }
                });

           }
            super.handleMessage(msg);
        }
    };
 @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameText=(EditText)findViewById(R.id.email);
        userPwText=(EditText)findViewById(R.id.password);
        loginBtn=(Button)findViewById(R.id.email_sign_in_button);

        sharedPreferences=getSharedPreferences("personal",Activity.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sent the userName and passwd to server
                //
                //1. check the format
                //2.sent to server.
                //3. require the response.
                //4.check response for(time out, yes:goto intent ,no: pop window guide to reload).
                if(!(userNameText.getText().equals(""))&&(!userPwText.getText().equals(""))){
                    new Thread(){
                        @Override
                        public void run() {

                            super.run();
                            // socket;
                            try {
                                socket = new Socket("192.168.253.1",8000);
                               // socket.setSoTimeout(10000);//set for 500ms connecting timeout.
                                new SocketHelper(socket).outPutString(userNameText.getText() + "$" + userPwText.getText());
                                String receiveString=new SocketHelper(socket).getInputString();

                                String []result=receiveString.split("\\$");
                                //System.out.println(result[1]);
                               // Log.i("System",receiveString);
                                if(result==null||result.length<2){
                                    throw new NullPointerException();
                                }
                                if(result[0].equals("login")&&result[1].equals("y")){
                                     handler.sendEmptyMessage(1);
                                }else{
                                     handler.sendEmptyMessage(2);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                //catch timeout exception.
                                //pop out a window says "Connecting failed"
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        new AlertDialog.Builder(LoginActivity.this).setTitle("您的网络有问题，请重试")
                                                .setPositiveButton("确定",null).show();
                                    }
                                });
                            }



                        }
                    }.start();


                }
                else {
                    Toast.makeText(LoginActivity.this,"请输入正确的用户名和密码",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
