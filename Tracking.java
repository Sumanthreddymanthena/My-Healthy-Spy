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
public class Tracking extends AppCompatActivity{
    String refrence_id;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16;
    Button b17,b18,b19,b20,b21,b22,b23,b24,b25,b26,b27,b28,b29,b30,b31;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<FitnessPlan> plan = new ArrayList<FitnessPlan>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracking_layout);
        if (ContextCompat.checkSelfPermission(Tracking.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // request permission (see result in onRequestPermissionsResult() method)
            ActivityCompat.requestPermissions(Tracking.this, new String[]{Manifest.permission.SEND_SMS}, 123);
        }
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
                Toast.makeText(Tracking.this, "Failed to read values from database", Toast.LENGTH_LONG).show();
            }
        });
        b1 = (Button) this.findViewById(R.id.one);
        b2 = (Button) this.findViewById(R.id.two);
        b3 = (Button) this.findViewById(R.id.three);
        b4 = (Button) this.findViewById(R.id.four);
        b5 = (Button) this.findViewById(R.id.five);
        b6 = (Button) this.findViewById(R.id.six);
        b7 = (Button) this.findViewById(R.id.seven);
        b8 = (Button) this.findViewById(R.id.eight);
        b9 = (Button) this.findViewById(R.id.nine);
        b10 = (Button) this.findViewById(R.id.ten);
        b11 = (Button) this.findViewById(R.id.eleven);
        b12 = (Button) this.findViewById(R.id.twelve);
        b13 = (Button) this.findViewById(R.id.thirteen);
        b14 = (Button) this.findViewById(R.id.fourteen);
        b15 = (Button) this.findViewById(R.id.fifteen);
        b16 = (Button) this.findViewById(R.id.sixteen);
        b17 = (Button) this.findViewById(R.id.seventeen);
        b18 = (Button) this.findViewById(R.id.eightteen);
        b19 = (Button) this.findViewById(R.id.ninteen);
        b20 = (Button) this.findViewById(R.id.twenty);
        b21 = (Button) this.findViewById(R.id.t1);
        b22 = (Button) this.findViewById(R.id.t2);
        b23 = (Button) this.findViewById(R.id.t3);
        b24 = (Button) this.findViewById(R.id.t4);
        b25 = (Button) this.findViewById(R.id.t5);
        b26 = (Button) this.findViewById(R.id.t6);
        b27 = (Button) this.findViewById(R.id.t7);
        b28 = (Button) this.findViewById(R.id.t8);
        b29 = (Button) this.findViewById(R.id.t9);
        b30 = (Button) this.findViewById(R.id.t10);
        b31 = (Button) this.findViewById(R.id.t11);

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
            if(!list.contains(i) && i <= current_day && i == 1)
                b1.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 2)
                b2.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 3)
                b3.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 4)
                b4.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 5)
                b5.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 6)
                b6.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 7)
                b7.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 8)
                b8.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 9)
                b9.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i ==10)
                b10.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 11)
                b11.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 12)
                b12.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 13)
                b13.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 14)
                b14.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 15)
                b15.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 16)
                b16.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 17)
                b17.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 18)
                b18.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 19)
                b19.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 20)
                b20.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 21)
                b21.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 22)
                b22.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 23)
                b23.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 24)
                b24.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 25)
                b25.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 26)
                b26.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 27)
                b27.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 28)
                b28.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 29)
                b29.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 30)
                b30.setBackgroundColor(Color.RED);
            if(!list.contains(i) && i <= current_day && i == 31)
                b31.setBackgroundColor(Color.RED);
        }

        for(int i=1;i<=31;i++){
            if(list.contains(i) && i <= current_day && i == 1)
                b1.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 2)
                b2.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 3)
                b3.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 4)
                b4.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 5)
                b5.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 6)
                b6.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 7)
                b7.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 8)
                b8.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 9)
                b9.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i ==10)
                b10.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 11)
                b11.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 12)
                b12.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 13)
                b13.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 14)
                b14.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 15)
                b15.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 16)
                b16.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 17)
                b17.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 18)
                b18.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 19)
                b19.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 20)
                b20.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 21)
                b21.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 22)
                b22.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 23)
                b23.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 24)
                b24.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 25)
                b25.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 26)
                b26.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 27)
                b27.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 28)
                b28.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 29)
                b29.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 30)
                b30.setBackgroundColor(Color.GREEN);
            if(list.contains(i) && i <= current_day && i == 31)
                b31.setBackgroundColor(Color.GREEN);
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length()-1);
            System.out.println(UserData.getPhone()+"=======");
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(UserData.getPhone(), null, "You missed workout on below days in current month\n"+sb.toString(), null, null);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profile) {
            Intent intent = new Intent(Tracking.this,Profile.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.usermodules) {
            Intent intent = new Intent(Tracking.this,UserModules.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.progresstracking) {
            Intent intent = new Intent(Tracking.this,Tracking.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.fooddiet) {
            Intent intent = new Intent(Tracking.this,FoodDiet.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.analytics) {
            Intent intent = new Intent(Tracking.this,AnalyticsChart.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.notifications) {
            Intent intent = new Intent(Tracking.this,Notifications.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.logout) {
            Intent intent = new Intent(Tracking.this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}