package com.example.fitnessapp;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SuggestLossDiet extends AppCompatActivity {
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestdiet_layout);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv1.setText("Weight Loss Diet Details");
        StringBuilder sb = new StringBuilder();
        sb.append("1) Oat meals which consist of high fiber and slow-burn properties.\n");
        sb.append("2) Beans are a great source of protein.\n");
        sb.append("3) Pureed vegetables add low-cal bulk to the tasty dish.\n");
        sb.append("4) Nuts are for a great snack on the run, take a small handful of almonds, peanuts, walnuts, or pecans.\n");
        sb.append("5) Apples, One reason is that raw fruit has more fiber. Plus, chewing sends signals to your brain that you’ve eaten something substantial.\n");
        sb.append("6) Grapefruits can also help you shed pounds, especially if you are at risk for diabetics.\n");
        tv2.setText(sb.toString());
        Button proceed = (Button) this.findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuggestLossDiet.this,WeightLossExercise.class);
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
            Intent intent = new Intent(SuggestLossDiet.this,Profile.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.usermodules) {
            Intent intent = new Intent(SuggestLossDiet.this,UserModules.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.progresstracking) {
            Intent intent = new Intent(SuggestLossDiet.this,Tracking.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.fooddiet) {
            Intent intent = new Intent(SuggestLossDiet.this,FoodDiet.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.analytics) {
            Intent intent = new Intent(SuggestLossDiet.this,AnalyticsChart.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.notifications) {
            Intent intent = new Intent(SuggestLossDiet.this,Notifications.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.logout) {
            Intent intent = new Intent(SuggestLossDiet.this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}