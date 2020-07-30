package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class firstpage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);

        Thread t1 = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                    Intent intent = new Intent(firstpage.this, register.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        t1.start();
    }
}
