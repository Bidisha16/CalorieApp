package com.example.project2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {
    EditText name2,age2,height2,weight2;
    Button edit_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        name2=findViewById(R.id.name2);
        age2=findViewById(R.id.age2);
        height2=findViewById(R.id.height2);
        weight2=findViewById(R.id.weight2);
        edit_profile=findViewById(R.id.edit_profile);

        SharedPreferences preferences=getSharedPreferences("pref",MODE_PRIVATE);
        String s=preferences.getString("n"," ");
        name2.setText(s);
        String s2=preferences.getString("h"," ");
        height2.setText(s2);
        String s3=preferences.getString("w"," ");
        weight2.setText(s3);
        String s4=preferences.getString("a"," ");
        age2.setText(s4);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(profile.this,basic_activity.class);
                startActivity(intent);
            }
        });
    }
}
