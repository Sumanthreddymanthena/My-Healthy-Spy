package com.example.fitnessapp;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
public class YogaExercise extends AppCompatActivity {
    TextView tv1;
    String names[] = {"Downward dog to Forward lunge","Side angle to Warrior II","Triangle to half moon","Upward dog to downward dog","Warrior I to Warrior III"};
    int images[] = {R.drawable.y1,R.drawable.y2,R.drawable.y3,R.drawable.y4,R.drawable.y5};
    ListView list;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<FitnessPlan> plan = new ArrayList<FitnessPlan>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exerciselist_layout);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("fitnessplan");

        Query qry = myRef.orderByKey();
        qry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                plan.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    FitnessPlan fp = postSnapshot.getValue(FitnessPlan.class);
                    plan.add(fp);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(YogaExercise.this, "Failed to read values from database", Toast.LENGTH_LONG).show();
            }
        });
        tv1 = (TextView) findViewById(R.id.textView1);
        tv1.setText("Yoga Exercises List");
        list = (ListView) findViewById(R.id.list);
        ImageList adapter = new ImageList(YogaExercise.this, names,images);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                saveWorkout(position);
            }
        });
    }
    public void saveWorkout(int index) {
        try {
            String user = UserData.getUsername();
            String exercise = names[index];
            java.util.Date dd = new java.util.Date();
            java.sql.Date date = new java.sql.Date(dd.getTime());
            java.sql.Time time = new java.sql.Time(dd.getTime());

            String id = "Record No : "+(plan.size() + 1);
            FitnessPlan fp = new FitnessPlan();
            fp.setId(id);
            fp.setUser(user);
            fp.setExercise(exercise);
            fp.setDate(date.toString());
            fp.setTime(time.toString());
            fp.setType("Yoga Exercise");
            myRef.child(id).setValue(fp);
            plan.add(fp);
            Toast.makeText(YogaExercise.this, "Yoga details recorded", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(YogaExercise.this,UserModules.class);
            startActivity(intent);
        }catch(Exception e){
            e.printStackTrace();
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
            Intent intent = new Intent(YogaExercise.this,Profile.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.usermodules) {
            Intent intent = new Intent(YogaExercise.this,UserModules.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.progresstracking) {
            Intent intent = new Intent(YogaExercise.this,Tracking.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.fooddiet) {
            Intent intent = new Intent(YogaExercise.this,FoodDiet.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.analytics) {
            Intent intent = new Intent(YogaExercise.this,AnalyticsChart.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.notifications) {
            Intent intent = new Intent(YogaExercise.this,Notifications.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.logout) {
            Intent intent = new Intent(YogaExercise.this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}