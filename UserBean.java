package com.example.fitnessapp;
import com.google.firebase.database.IgnoreExtraProperties;
public class UserBean {
    String username, password, contactno, email, address;
public void setUsername(String username) {
    this.username = username;
}
public String getUsername(){
    return username;
}
public void setPassword(String password){
    this.password = password;
}
public String getPassword(){
    return password;
}
public void setContactno(String contactno) {
    this.contactno = contactno;
}
public String getContactno() {
    return contactno;
}

public void setEmail(String email) {
    this.email = email;
}
public  String getEmail() {
    return email;
}
public void setAddress(String address) {
    this.address = address;
}
public String getAddress() {
    return address;
}
}
