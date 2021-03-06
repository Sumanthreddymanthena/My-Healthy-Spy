package com.example.fitnessapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Map;

public class AnalyticsChart extends Activity {
    String user,value;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<FitnessPlan> plan = new ArrayList<FitnessPlan>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analyticschart_layout);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("fitnessplan");
        plan.clear();
        Query qry = myRef.orderByKey();
        qry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    FitnessPlan fp = postSnapshot.getValue(FitnessPlan.class);
                    plan.add(fp);
                }
                displayChart();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AnalyticsChart.this, "Failed to read values from database", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void displayChart() {
        System.out.println(plan.size()+"===============*****");
        LinkedHashMap<String,Integer> names = new LinkedHashMap<String,Integer>();
        for(int i=0;i<plan.size();i++){
            FitnessPlan fp = plan.get(i);
            String time[] = fp.getTime().toString().split(":");
            if(names.containsKey(time[0])){
                int count = names.get(time[0]) + 1;
                names.put(time[0],count);
            } else {
                names.put(time[0],1);
            }
        }
        System.out.println(names+"=====================");

        BarChart chart = (BarChart) findViewById(R.id.chart);
        BarDataSet dataset = new BarDataSet(getDataSet(names), "Analytics Graph");
        BarData data = new BarData(getXAxisValues(names), dataset);
        chart.setData(data);
        chart.setDescription("Analytics Graph");
        chart.animateXY(2000, 2000);
        chart.invalidate();

    }

    private ArrayList<BarEntry> getDataSet(LinkedHashMap<String,Integer> names) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        int index = 0;
        for(Map.Entry<String,Integer> me : names.entrySet()) {
            entries.add(new BarEntry(me.getValue(), index));
            index = index + 1;
        }
        return entries;
    }

    private ArrayList<String> getXAxisValues(LinkedHashMap<String,Integer> names) {
        ArrayList<String> xAxis = new ArrayList<>();
        for(Map.Entry<String,Integer> me : names.entrySet()) {
            xAxis.add(me.getKey());
        }
        return xAxis;
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
            Intent intent = new Intent(AnalyticsChart.this,Profile.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.usermodules) {
            Intent intent = new Intent(AnalyticsChart.this,UserModules.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.progresstracking) {
            Intent intent = new Intent(AnalyticsChart.this,Tracking.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.fooddiet) {
            Intent intent = new Intent(AnalyticsChart.this,FoodDiet.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.analytics) {
            Intent intent = new Intent(AnalyticsChart.this,AnalyticsChart.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.notifications) {
            Intent intent = new Intent(AnalyticsChart.this,Notifications.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.logout) {
            Intent intent = new Intent(AnalyticsChart.this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
