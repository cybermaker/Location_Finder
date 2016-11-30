package com.example.toshiba.location_finder;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Mainpage extends AppCompatActivity {
    private Userdata userdata;
    private Databasehelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        ListView userlist = (ListView) findViewById(R.id.userlist);
        dbhelper = new Databasehelper(this);
        ArrayList<String> userlistadapter = new ArrayList<>();
        Cursor data = dbhelper.readallusername();
        while (data.moveToNext()) {
            userlistadapter.add(data.getString(0));
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userlistadapter);
            userlist.setAdapter(listAdapter);
        }
        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                String chosen = item.toString();
                double lats = dbhelper.searchlat(chosen);
                double longs = dbhelper.searchlong(chosen);
                Intent location = new Intent(Mainpage.this,MapsActivity.class);
                location.putExtra("latitude",lats);
                location.putExtra("longtitude",longs);
                startActivity(location);
            }
        });
    }
}
