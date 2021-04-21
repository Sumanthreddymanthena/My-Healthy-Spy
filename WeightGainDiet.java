package com.example.fitnessapp;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class WeightGainDiet extends AppCompatActivity {
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietdetails_layout);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv1.setText("Weight Gain Diet Details");
        StringBuilder sb = new StringBuilder();
        sb.append("1) Lean proteins, such as chicken and fish.\n");
        sb.append("2) Red meat with no growth hormones, such as grass-fed beef.\n");
        sb.append("3) Eggs.\n");
        sb.append("4) Full-fat dairy, such as whole milk and full-fat Greek yogurt.\n");
        sb.append("5) Fat-rich  fruits, such as avocados.\n");
        sb.append("6) Nuts, such as almonds.\n");
        sb.append("7) Whole-grain breads.\n");
        tv2.setText(sb.toString());
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
            Intent intent = new Intent(WeightGainDiet.this,Profile.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.usermodules) {
            Intent intent = new Intent(WeightGainDiet.this,UserModules.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.progresstracking) {
            Intent intent = new Intent(WeightGainDiet.this,Tracking.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.fooddiet) {
            Intent intent = new Intent(WeightGainDiet.this,FoodDiet.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.analytics) {
            Intent intent = new Intent(WeightGainDiet.this,AnalyticsChart.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.notifications) {
            Intent intent = new Intent(WeightGainDiet.this,Notifications.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.logout) {
            Intent intent = new Intent(WeightGainDiet.this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}