package com.example.fitnessapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class Login extends AppCompatActivity{
    EditText username,password;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<UserBean> userList = new ArrayList<UserBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("fitnessusers");

        Query qry = myRef.orderByKey();
        qry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserBean ub = postSnapshot.getValue(UserBean.class);
                    userList.add(ub);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Login.this, "Failed to read values from database", Toast.LENGTH_LONG).show();
            }
        });

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Button login = (Button) this.findViewById(R.id.submit);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (uname.trim().length() == 0 || uname == null) {
                    Toast.makeText(Login.this, "Please enter username", Toast.LENGTH_LONG).show();
                    username.requestFocus();
                    return;
                }
                if (pass.trim().length() == 0 || pass== null) {
                    Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_LONG).show();
                    password.requestFocus();
                    return;
                }
                boolean flag = false;
                System.out.println(userList.size()+"========");
                String phone = "none";
                for(int i=0;i<userList.size();i++) {
                    UserBean ub = userList.get(i);
                    System.out.println(ub.getUsername()+" "+ub.getPassword()+" "+uname+" "+pass);
                    if(ub.getUsername().equals(uname) && pass.equals(ub.getPassword())){
                        flag = true;
                        phone = ub.getContactno();
                        break;
                    }
                }
                if(flag) {
                    Intent intent = new Intent(Login.this,UserModules.class);
                    UserData.setUsername(uname);
                    UserData.setPhone(phone);
                    intent.putExtra("user", uname);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Login.this, "Invalid Login Details", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
