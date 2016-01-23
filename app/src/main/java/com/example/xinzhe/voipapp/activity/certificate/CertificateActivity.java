package com.example.xinzhe.voipapp.activity.certificate;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xinzhe.voipapp.R;

public class CertificateActivity extends AppCompatActivity {
    TextView textViewName;
    TextView textViewBalan;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);
        sharedPreferences=getSharedPreferences("personal", Activity.MODE_PRIVATE);
        textViewName=(TextView)findViewById(R.id.textViewCerName);
        textViewBalan=(TextView)findViewById(R.id.textViewCerBalan);

        textViewName.setText("你好，"+sharedPreferences.getString("name","")+",id:"
                +sharedPreferences.getString("id",""));
        textViewBalan.setText("您当前余额为："+sharedPreferences.getString("balance",""));
    }
}
