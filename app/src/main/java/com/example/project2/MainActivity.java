package com.example.project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText user, pass,cal;
    Button login;
    TextView new_user;
    sqlitehelper sqlitehelper=new sqlitehelper(this);
    sqlitehelper3 sql3=new sqlitehelper3(this);
    Totaldatabase totaldatabase=new Totaldatabase(this);
    Calendar mcurr;
    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        new_user=findViewById(R.id.new_user);
        mcurr=Calendar.getInstance();
        day=mcurr.get(Calendar.DAY_OF_MONTH);
        month=mcurr.get(Calendar.MONTH);
        year=mcurr.get(Calendar.YEAR);
        month=month+1;




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String s=day+"/"+month+"/"+year;
                SharedPreferences preferences=getSharedPreferences("pref",MODE_PRIVATE);
                String s1=preferences.getString("date"," ");


                boolean isValid = sqlitehelper.login(user.getText().toString(), pass.getText().toString());
                if (isValid) {
                     boolean isFirstDialog=getSharedPreferences("PREF",MODE_PRIVATE)
                             .getBoolean("isFirstDialog",true);
                     if (isFirstDialog || !(s.equals(s1))) {
                         totaldatabase.delete_ca();
                         sql3.delete_cal();
                         LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                         View view1 = inflater.inflate(R.layout.custom_dialog, null);
                         cal = view1.findViewById(R.id.calorie);
                         Button btn = view1.findViewById(R.id.cal_btn);

                         AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                         builder.setView(view1);

                         builder.setCancelable(true);
                         builder.show();
                         btn.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                 long l=totaldatabase.Set_total(Integer.parseInt(cal.getText().toString()));
                                 boolean isFirstDialog = getSharedPreferences("PREF", MODE_PRIVATE)
                                         .getBoolean("isFirstDialog", true);
                                 if (isFirstDialog) {
                                     Toast.makeText(MainActivity.this, "Calorie added", Toast.LENGTH_SHORT).show();
                                     startActivity(new Intent(MainActivity.this,basic_activity.class));
                                     finish();
                                 } else {
                                     startActivity(new Intent(MainActivity.this, basic_activity.class));
                                     finish();
                                 }
                                 getSharedPreferences("PREF", MODE_PRIVATE).edit()
                                         .putBoolean("isFirstDialog", false).commit();
                             }
                         });

                     }else{
                        startActivity(new Intent(MainActivity.this,basic_activity.class));
                     }

                } else {
                    Toast.makeText(MainActivity.this, "Wrong Username OR Password", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
