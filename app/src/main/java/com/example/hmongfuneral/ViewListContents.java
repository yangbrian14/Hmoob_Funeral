package com.example.hmongfuneral;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;



    public class ViewListContents extends AppCompatActivity {

        DatabaseHelper myDB;
        ArrayList<User> userList;
        ListView listView;
        User user;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.viewcontents_layout);

            myDB = new DatabaseHelper(this);

            userList = new ArrayList<>();
            Cursor data = myDB.getListContents();
            int numRows = data.getCount();
            if(numRows == 0){
                Toast.makeText(ViewListContents.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
            }else{
                int i=0;
                while(data.moveToNext()){
                    user = new User(data.getString(1),data.getString(2),data.getString(3));
                    userList.add(i,user);
                    System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                    System.out.println(userList.get(i).getFirstName());
                    i++;
                }
                ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, userList);
                listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(adapter);
            }
        }
    }


