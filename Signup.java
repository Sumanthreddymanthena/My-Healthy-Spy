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
public class Signup extends AppCompatActivity{
    EditText username,password,email,contact,address;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<UserBean> userList = new ArrayList<UserBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("fitnessusers");
        System.out.println(myRef.getParent()+"======");
        Query qry = myRef.orderByKey();
        qry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    System.out.println(postSnapshot.getValue()+"==============");
                    UserBean ub = postSnapshot.getValue(UserBean.class);
                    userList.add(ub);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Signup.this, "Failed to read values from database", Toast.LENGTH_LONG).show();
            }
        });


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        contact = (EditText) findViewById(R.id.contact);
        address = (EditText) findViewById(R.id.address);
        Button save = (Button) this.findViewById(R.id.submit);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String emailid = email.getText().toString().trim();
                String phone = contact.getText().toString().trim();
                String add = address.getText().toString().trim();

                if (uname.trim().length() == 0 || uname == null) {
                    Toast.makeText(Signup.this, "Please enter username", Toast.LENGTH_LONG).show();
                    username.requestFocus();
                    return;
                }
                if (pass.trim().length() == 0 || pass == null) {
                    Toast.makeText(Signup.this, "Please enter password", Toast.LENGTH_LONG).show();
                    password.requestFocus();
                    return;
                }
                if (emailid.trim().length() == 0 || emailid == null) {
                    Toast.makeText(Signup.this, "Please enter email id", Toast.LENGTH_LONG).show();
                    email.requestFocus();
                    return;
                }
                if (phone.trim().length() == 0 || phone == null) {
                    Toast.makeText(Signup.this, "Please enter contact no", Toast.LENGTH_LONG).show();
                    contact.requestFocus();
                    return;
                }
                if (add.trim().length() == 0 || add== null) {
                    Toast.makeText(Signup.this, "Please enter address", Toast.LENGTH_LONG).show();
                    address.requestFocus();
                    return;
                }
                boolean flag = false;
                System.out.println("=================="+userList.size());
                for(int i=0;i<userList.size();i++) {
                    UserBean ub = userList.get(i);
                    if(ub.getUsername().equals(uname)){
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    UserBean ub = new UserBean();
                    ub.setUsername(uname);
                    ub.setPassword(pass);
                    ub.setContactno(phone);
                    ub.setEmail(emailid);
                    ub.setAddress(add);
                    myRef.child(uname).setValue(ub);
                    userList.add(ub);
                    Toast.makeText(Signup.this, "Signup process completed", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Signup.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Signup.this, "Given username already exists", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}

