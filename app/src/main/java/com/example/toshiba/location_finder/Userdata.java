package com.example.toshiba.location_finder;

/**
 * Created by toshiba on 11/27/2016.
 */
public class Userdata {
    int id;
    double latitude,longtitude;
    String username,password;
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setpassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public double getLatitude(){
        return this.latitude;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    public double getLongtitude(){
        return this.longtitude;
    }
    public void setLon(double longtitude){
        this.longtitude = longtitude;
    }
}
