package com.example.toshiba.location_finder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LOGIN extends AppCompatActivity {
    private Button signin;
    private Button signup;
    private Button cancel;
    private EditText username;
    private EditText password;
    Databasehelper helper = new Databasehelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signin = (Button)findViewById(R.id.signin);
        signup = (Button)findViewById(R.id.signup);
        cancel = (Button)findViewById(R.id.cancel);
        username = (EditText)findViewById(R.id.usn);
        password = (EditText)findViewById(R.id.pass);
    }


    public void onsignupclick(View view){
        Intent signup = new Intent(this,Signup.class);
        startActivity(signup);
    }
    public void onsigninclick(View view){
        String userlog = username.getText().toString();
        String passlog = password.getText().toString();
        String passcode = helper.searchpass(userlog);
        if(passlog.equals(passcode)){
            Toast toast = Toast.makeText(this,"Login Success",Toast.LENGTH_LONG);
            toast.show();
            Intent signin = new Intent(this,Mainpage.class);
            startActivity(signin);
        }
        else{
            Toast toast = Toast.makeText(this,"Login Fail",Toast.LENGTH_LONG);
            toast.show();
            finish();
        }

    }
}
