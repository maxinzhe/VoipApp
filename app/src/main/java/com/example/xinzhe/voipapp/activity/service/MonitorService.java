package com.example.xinzhe.voipapp.activity.service;

import android.app.Notification;
import android.app.PendingIntent;
import  android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.xinzhe.voipapp.R;
import com.example.xinzhe.voipapp.activity.certificate.CertificateActivity;
import com.example.xinzhe.voipapp.socket.MySocket;
import com.example.xinzhe.voipapp.util.SocketHelper;

import java.net.Socket;

/**
 * Created by Xinzhe on 2016/1/20.
 */
public class MonitorService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        NotificationCompat.Builder mBuiler=new NotificationCompat.Builder(this)
      .setContentTitle("new mail from ")
                .setContentText("hello")
                .setSmallIcon(R.mipmap.ic_launcher);





        Intent intentToActivity=new Intent(this, CertificateActivity.class);
        TaskStackBuilder stackBuilder =TaskStackBuilder.create(this);
        stackBuilder.addParentStack(CertificateActivity.class);
        stackBuilder.addNextIntent(intentToActivity);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intentToActivity,0);
        mBuiler.setContentIntent(resultPendingIntent);
        startForeground(1,mBuiler.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Socket socket=new MySocket().getSocket();
        final SocketHelper socketHelper=new SocketHelper(socket);

        new Thread(new Runnable(){

            @Override
            public void run() {
                while(true){
                   String newString=socketHelper.getInputString();//may stuck to listen

                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }




}
