package com.example.fitnessapp;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
public class UserModules extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermodules_layout);
        initClickListner();
    }

    private void initClickListner() {
        Button weightgain = (Button) findViewById(R.id.weightgain);
        weightgain.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserModules.this,SuggestGainDiet.class);
                startActivity(intent);
            }
        });
        Button weightloss = (Button) findViewById(R.id.weightloss);
        weightloss.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserModules.this,SuggestLossDiet.class);
                startActivity(intent);
            }
        });
        Button yoga = (Button) findViewById(R.id.yoga);
        yoga.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserModules.this,SuggestYogaDiet.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profile) {
            Intent intent = new Intent(UserModules.this,Profile.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.usermodules) {
            Intent intent = new Intent(UserModules.this,UserModules.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.progresstracking) {
            Intent intent = new Intent(UserModules.this,Tracking.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.fooddiet) {
            Intent intent = new Intent(UserModules.this,FoodDiet.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.analytics) {
            Intent intent = new Intent(UserModules.this,AnalyticsChart.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.notifications) {
            Intent intent = new Intent(UserModules.this,Notifications.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.logout) {
            Intent intent = new Intent(UserModules.this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}