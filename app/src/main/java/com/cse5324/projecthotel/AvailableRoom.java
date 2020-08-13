package com.cse5324.projecthotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class AvailableRoom extends AppCompatActivity {

    Spinner spinner;
    String hotelNames="",strDate = "";
    EditText date,times;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reservations);

        getSupportActionBar().setTitle("View Reservations");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //go back button

        date = (EditText)findViewById(R.id.Date);
        times = (EditText)findViewById(R.id.Time);

        Date myDate = new Date();

        SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
        strDate = sm.format(myDate);

        date.setText(strDate);

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
        String[] time = cal.getTime().toString().split(" ");

        times.setText(time[3]);

        spinner = (Spinner) findViewById(R.id.hotelName);
        List<String> list = new ArrayList<String>();

        list.add("Maverick");
        list.add("Liberty");
        list.add("Ranger");
        list.add("Shard");
        list.add("Williams");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hotelNames = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        enter = (Button) findViewById(R.id.enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AvailableRoom.this, AvailableRoomList.class);
                myIntent.putExtra("hotelname",hotelNames);
                myIntent.putExtra("date",strDate);
                startActivity(myIntent);
            }
        });

    }
    //go back button to work
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
