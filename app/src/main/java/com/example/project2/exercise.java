package com.example.project2;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class exercise extends AppCompatActivity {
    EditText exercise,date_ex;
    Button submit2;
    ImageView cal_ex;
    Calendar mcurr;
    int day,month,year;
    Totaldatabase total=new Totaldatabase(this);
    private  final String Channel_id="happy notification";
    private final int Notification_id=001;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        exercise=findViewById(R.id.exercised_calories);
        date_ex=findViewById(R.id.date_ex);
        cal_ex=findViewById(R.id.ex_cal);
        submit2=findViewById(R.id.submit2);
        mcurr= Calendar.getInstance();
        day=mcurr.get(Calendar.DAY_OF_MONTH);
        month=mcurr.get(Calendar.MONTH);
        year=mcurr.get(Calendar.YEAR);
        month=month+1;

        date_ex.setText(day+"/"+month+"/"+year);
        cal_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog=new DatePickerDialog(exercise.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dateofmonth) {
                        monthofyear=monthofyear+1;
                        date_ex.setText(dateofmonth+"/"+monthofyear+"/"+year);

                    }
                },year,month,day);
                dialog.show();
            }
        });



        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rem=Integer.parseInt(total.disp_remain())+Integer.parseInt(exercise.getText().toString());
                long up=total.up_rem(rem);
                createNotifcicationchannel();
                NotificationCompat.Builder builder=new NotificationCompat.Builder(exercise.this,Channel_id);
                builder.setSmallIcon(R.drawable.ic_sentiment_very_satisfied_black_24dp);
                builder.setContentTitle("Motivation");
                builder.setContentText("Congrats, You have burnt"+" "+exercise.getText().toString()+" "+"calories");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(exercise.this);
                notificationManagerCompat.notify(Notification_id,builder.build());
                Intent intent=new Intent(exercise.this,basic_activity.class);
                startActivity(intent);
                finish();

                SharedPreferences preferences = getSharedPreferences("pref", MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();
                String exer= exercise.getText().toString();
                editor.putString("e",exer);
                editor.commit();

            }
        });
    }
    private  void createNotifcicationchannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="happy notfication";
            String desp="motivation notfication";
            int piror= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel=new NotificationChannel(Channel_id,name,piror);
            notificationChannel.setDescription(desp);
            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
