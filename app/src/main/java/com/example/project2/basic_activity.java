package com.example.project2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class basic_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView user,mail,tot_cal,rem_cal;
    sqlitehelper sql=new sqlitehelper(this);
    Totaldatabase total=new Totaldatabase(this);
    LinearLayout add,show,excercise;
    Totaldatabase getTotal=new Totaldatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View navgview=navigationView.getHeaderView(0);
        user=navgview.findViewById(R.id.username);
        mail=navgview.findViewById(R.id.email);
        user.setText(sql.show_username());
        mail.setText(sql.show_mail());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        tot_cal=findViewById(R.id.tv1);
        rem_cal=findViewById(R.id.tv2);
        tot_cal.setText(total.disp_total());
        rem_cal.setText(total.disp_remain());
        add=findViewById(R.id.add);
        show=findViewById(R.id.show);
        excercise=findViewById(R.id.exercise);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(basic_activity.this,add_expense.class));
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(basic_activity.this,show_expense.class));
            }
        });
        excercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(basic_activity.this,exercise.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.basic_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.edit_profile) {
            startActivity(new Intent(this,edit_profile.class));

        } else if (id==R.id.logout){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        } else if (id==R.id.new_cal){
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view1 = inflater.inflate(R.layout.custom_dialog, null);
            final EditText cal = view1.findViewById(R.id.calorie);
            Button btn = view1.findViewById(R.id.cal_btn);

            AlertDialog.Builder builder = new AlertDialog.Builder(basic_activity.this);
            builder.setView(view1);
            builder.setCancelable(true);
            builder.show();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getTotal.up_new(Integer.parseInt(cal.getText().toString()));
                    startActivity(new Intent(basic_activity.this,basic_activity.class));
                    finish();
                }
            });
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.profile) {
            startActivity(new Intent(basic_activity.this,profile.class));

        } else if (id == R.id.about) {
            startActivity(new Intent(basic_activity.this,about.class));

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
