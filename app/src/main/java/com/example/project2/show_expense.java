package com.example.project2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.R;
import com.example.project2.sqlitehelper3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class show_expense extends AppCompatActivity {
    ListView ls;
    ArrayList<extra> arrayList;
    sqlitehelper3 sql3=new sqlitehelper3(this);
    Custom_adapter customAdapter;
    String name[];


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_expense);
        ls=findViewById(R.id.list);
        arrayList=sql3.show_cal();

        for(extra e : arrayList){
            Log.d("eee",e.getDate()+"  "+e.getCat()+"  "+e.getExp());
        }


        name = new String[arrayList.size()];
        int i= 0;
        for(extra e : arrayList){
            name[i] = e.getCat();
            i++;
        }
        customAdapter=new Custom_adapter(this,arrayList,name);
        ls.setAdapter(customAdapter);


    }


}
