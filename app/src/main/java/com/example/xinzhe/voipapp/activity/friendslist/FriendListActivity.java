package com.example.xinzhe.voipapp.activity.friendslist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xinzhe.voipapp.R;
import com.example.xinzhe.voipapp.database.MyDataBaseHelp;
import com.example.xinzhe.voipapp.database.VoipDataBase;
import com.example.xinzhe.voipapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xinzhe on 2016/1/13.
 */
public class FriendListActivity extends Activity{
    VoipDataBase voipDataBase;
    List<Contact> contactsList=new ArrayList();
    List<String> list=new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendslist);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        voipDataBase= VoipDataBase.getInstance(FriendListActivity.this);
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FriendListActivity.this, "你选择了" + position, Toast.LENGTH_SHORT).show();//
                //BaseOnThePreviousButton to jump to functional Activity.
            }
        });
        loadContacts();
    }
    private void loadContacts(){
        contactsList = voipDataBase.queryContacts();
        if(contactsList.size()>0){
            list.clear();
            for(Contact contact:contactsList){
                list.add(contact.getName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);

        }
    }
}
