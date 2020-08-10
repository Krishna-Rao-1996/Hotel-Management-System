package com.cse5324.projecthotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewSelectedRoom extends AppCompatActivity {
    private Button button1;
    private Button button2;
    EditText Hotelname,StartDate,Roomtype,Roomnumber,RoomOccupiedStatus,RoomAvailableStatus,RoomRate;
    String [] stringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_room);

        getSupportActionBar().setTitle("View Selected Room");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Hotelname = (EditText)findViewById(R.id.hotelName);

        Intent intent = getIntent();
        stringArray = intent.getStringArrayExtra("listselected");

        Hotelname = (EditText)findViewById(R.id.hotelName);
        StartDate = (EditText)findViewById(R.id.StartDate);
        Roomtype = (EditText)findViewById(R.id.roomType);
        Roomnumber = (EditText)findViewById(R.id.RoomNumber);
        RoomOccupiedStatus = (EditText)findViewById(R.id.roomOcuupiedStatus);
        RoomAvailableStatus = (EditText)findViewById(R.id.roomAvailableStatus);
        RoomRate = (EditText)findViewById(R.id.roomRate);

        StartDate.setText(stringArray[0]);
        Hotelname.setText(stringArray[4]);
        Roomtype.setText(stringArray[1]);
        RoomOccupiedStatus.setText(stringArray[3]);
        RoomAvailableStatus.setText(stringArray[2]);
        Roomnumber.setText(stringArray[5]);
        RoomRate.setText(stringArray[6]);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button2.setVisibility(View.VISIBLE);

                Hotelname.setEnabled(true);
                StartDate.setEnabled(true);
                Roomtype.setEnabled(true);
                RoomOccupiedStatus.setEnabled(true);
                RoomAvailableStatus.setEnabled(true);
                Roomnumber.setEnabled(true);
                RoomRate.setEnabled(true);
            }
        });
    }

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