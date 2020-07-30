package com.example.project2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    Button reg;
    EditText user1,pass1,mail;
    sqlitehelper sqlitehelper=new sqlitehelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        reg=findViewById(R.id.reg);
        user1=findViewById(R.id.user1);
        pass1=findViewById(R.id.pass1);
        mail=findViewById(R.id.mail);


        SharedPreferences preferences = getSharedPreferences("pref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();


        boolean isFirstTime = preferences.getBoolean("isFirstTime", true);
        if(isFirstTime) {

            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long l = sqlitehelper.add_user(user1.getText().toString(),pass1.getText().toString(),mail.getText().toString());
                    if (l > 0) {
                        Toast.makeText(register.this, "user added", Toast.LENGTH_LONG).show();
                    }
                    editor.putBoolean("isFirstTime", false);
                    editor.commit();
                    startActivity(new Intent(register.this, MainActivity.class));
                }
                });

            }
        else{
                startActivity(new Intent(register.this,MainActivity.class));
                finish();
            }
        }
    }
