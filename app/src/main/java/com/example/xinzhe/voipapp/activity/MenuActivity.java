package com.example.xinzhe.voipapp.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xinzhe.voipapp.R;
import com.example.xinzhe.voipapp.activity.certificate.CertificateActivity;
import com.example.xinzhe.voipapp.activity.friendslist.FriendListActivity;
import com.example.xinzhe.voipapp.activity.pay.PayActivity;

/**
 * Created by Xinzhe on 2016/1/13.
 */
public class MenuActivity extends Activity implements View.OnClickListener{
    Button voipBtn;
    Button payBtn;
    Button certifiBtn;
    Button contactsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        voipBtn=(Button)findViewById(R.id.voipbutton);
        payBtn=(Button)findViewById(R.id.paybutton2);
        certifiBtn=(Button) findViewById(R.id.certibutton3);
        contactsBtn=(Button)findViewById(R.id.contactsbutton4);

        voipBtn.setOnClickListener(this);
        payBtn.setOnClickListener(this);
       /*// payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new
            }
        });*/
        certifiBtn.setOnClickListener(this);
        contactsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       // Toast.makeText(this,"aaa",Toast.LENGTH_SHORT).show();
        Log.i("onclick","in Onclick");
        Intent intent=new Intent();
        switch(v.getId()){
            case R.id.voipbutton:
        //        Log.i("onclick","in the case of voipbutton");
                intent.setClass(MenuActivity.this, FriendListActivity.class);
                break;
            case R.id.paybutton2:
                intent.setClass(MenuActivity.this, PayActivity.class);

         //       startActivity(intent);
                break;
            case R.id.certibutton3:
                intent.setClass(MenuActivity.this, CertificateActivity.class);
                break;
            case R.id.contactsbutton4:
                intent.setClass(MenuActivity.this, FriendListActivity.class);
                break;
        }
        startActivity(intent);
    }
}
