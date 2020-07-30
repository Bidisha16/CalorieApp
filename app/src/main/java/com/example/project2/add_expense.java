package com.example.project2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class add_expense extends AppCompatActivity {
    EditText cat,exp,date;
    Button add;
    ImageView cal;
    sqlitehelper3 sq3=new sqlitehelper3(this);;
    Totaldatabase totaldatabase=new Totaldatabase(this);
    Calendar mcurr;
    int day,month,year;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);
        cal=findViewById(R.id.calendar);
        cat=findViewById(R.id.cat);
        exp=findViewById(R.id.exp);
        add=findViewById(R.id.add1);
        date=findViewById(R.id.date);
        mcurr=Calendar.getInstance();
        day=mcurr.get(Calendar.DAY_OF_MONTH);
        month=mcurr.get(Calendar.MONTH);
        year=mcurr.get(Calendar.YEAR);
        month=month+1;
        date.setText(day+"/"+month+"/"+year);
        SharedPreferences preferences=getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String d=date.getText().toString();
        editor.putString("date",d);
        editor.apply();


        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog=new DatePickerDialog
                        (add_expense.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dateofmonth) {
                        monthofyear=monthofyear+1;
                        date.setText(dateofmonth+"/"+monthofyear+"/"+year);

                    }
                },year,month,day);
                dialog.show();
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cat.getText().toString()!="" || exp.getText().toString()!="") {

                    sq3.add_user(cat.getText().toString(),Integer.parseInt(exp.getText().toString()),date.getText().toString());
                    int i=Integer.parseInt(totaldatabase.disp_remain())-Integer.parseInt(exp.getText().toString());
                    long l=totaldatabase.up_rem(i);
                    startActivity(new Intent(add_expense.this,basic_activity.class));
                    finish();
                    Toast.makeText(add_expense.this, "Calories added", Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}
