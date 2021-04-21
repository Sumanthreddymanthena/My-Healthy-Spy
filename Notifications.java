package com.example.fitnessapp;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.telephony.SmsManager;
public class Notifications extends AppCompatActivity{
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<FitnessPlan> plan = new ArrayList<FitnessPlan>();
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietdetails_layout);
        if (ContextCompat.checkSelfPermission(Notifications.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // request permission (see result in onRequestPermissionsResult() method)
            ActivityCompat.requestPermissions(Notifications.this, new String[]{Manifest.permission.SEND_SMS}, 123);
        }
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv1.setText("Notifications. Sms will be sent for missing days workout");
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
                progressTracking();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Notifications.this, "Failed to read values from database", Toast.LENGTH_LONG).show();
            }
        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    System.out.println("granted======================");
                } else {
                    // permission denied
                }
                return;
            }
        }
    }

    public void progressTracking() {
        java.util.Date dd = new java.util.Date();
        java.sql.Date date = new java.sql.Date(dd.getTime());
        String day = date.toString().split("-")[2];
        int current_day = Integer.parseInt(day);
        day = day.trim();
        ArrayList<Integer> list = new ArrayList<Integer>();
        String contact = "none";
        for(int i=0;i<plan.size();i++){
            FitnessPlan fp = plan.get(i);
            if(fp.getUser().equals(UserData.getUsername())) {
                String work_date = fp.getDate().toString().split("-")[2];
                int work_day = Integer.parseInt(work_date);
                list.add(work_day);
            }
        }
        System.out.println(plan.size()+" "+list.toString()+" ==== "+current_day);
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=31;i++){
            if(!list.contains(i) && i <= current_day)
                sb.append(i+",");
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length()-1);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(UserData.getPhone(), null, "You missed workout on below days in current month\n"+sb.toString(), null, null);
            tv2.setText("\n\n\n\nYou missed workout on below days in current month\n"+sb.toString());
        }
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
            Intent intent = new Intent(Notifications.this,Profile.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.usermodules) {
            Intent intent = new Intent(Notifications.this,UserModules.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.progresstracking) {
            Intent intent = new Intent(Notifications.this,Tracking.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.fooddiet) {
            Intent intent = new Intent(Notifications.this,FoodDiet.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.analytics) {
            Intent intent = new Intent(Notifications.this,AnalyticsChart.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.notifications) {
            Intent intent = new Intent(Notifications.this,Notifications.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.logout) {
            Intent intent = new Intent(Notifications.this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
