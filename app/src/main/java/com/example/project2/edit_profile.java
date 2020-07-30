package com.example.project2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class edit_profile extends AppCompatActivity {
    EditText name,height,weight,age;
    Button submit;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        name=findViewById(R.id.name);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        age=findViewById(R.id.age);
        submit=findViewById(R.id.edit);

        preferences = getSharedPreferences("pref", MODE_PRIVATE);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                SharedPreferences.Editor editor = preferences.edit();
                String nam=name.getText().toString();
                editor.putString("n",nam);

                String hei=height.getText().toString();
                editor.putString("h",hei);
                String wei=weight.getText().toString();
                editor.putString("w",wei);
                String ag=age.getText().toString();
                editor.putString("a",ag);
                //editor.apply();
                editor.commit();
                Toast.makeText(edit_profile.this, "Profile Edited", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(edit_profile.this,basic_activity.class);
                startActivity(intent);
            }
        });
    }
}
