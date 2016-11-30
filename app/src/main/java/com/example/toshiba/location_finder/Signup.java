package com.example.toshiba.location_finder;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Signup extends AppCompatActivity implements SensorEventListener {
    private EditText userup;
    private EditText passup;
    private TextView latitude;
    private TextView longtitude;
    private Button random;
    private Button adduser;
    private Random randlad = new Random();
    private Random randlong = new Random();
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 1200;
    private Databasehelper helper = new Databasehelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userup = (EditText)findViewById(R.id.user_s);
        passup = (EditText)findViewById(R.id.pass_s);
        latitude = (TextView)findViewById(R.id.lat);
        longtitude = (TextView)findViewById(R.id.lon);
        random = (Button)findViewById(R.id.clickrnd);
        adduser = (Button)findViewById(R.id.adduser);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.create,menu);
        return super.onCreateOptionsMenu(menu);
    }
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                   double lat = randlad.nextInt(170) - 85 + randlad.nextDouble();
                   double lon = randlong.nextInt(360) - 180 + randlong.nextDouble();
                   latitude.setText(Double.toString(lat));
                   longtitude.setText(Double.toString(lon));
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }
    public void onrandomclick(View view){
        double lat = randlad.nextInt(170)-85+randlad.nextDouble();
        double lon = randlong.nextInt(360)-180+randlong.nextDouble();
        latitude.setText(Double.toString(lat));
        longtitude.setText(Double.toString(lon));
    }
    public void onAdd(View view){
        String username = userup.getText().toString();
        String password = passup.getText().toString();
        double latitudein =Double.parseDouble(latitude.getText().toString());
        double longtitudein =Double.parseDouble(longtitude.getText().toString());
        Userdata user = new Userdata();
        user.setUsername(username);
        user.setpassword(password);
        user.setLatitude(latitudein);
        user.setLon(longtitudein);
        helper.Insertuser(user);
        startActivity(new Intent(Signup.this,LOGIN.class));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()== R.id.back){
            startActivity(new Intent(Signup.this,LOGIN.class));
            return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
