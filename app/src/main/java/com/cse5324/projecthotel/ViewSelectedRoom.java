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
    EditText Hotelname,StartDate,Roomtype,Roomnumber,RoomOccupiedStatus,RoomAvailableStatus;
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

        String temp = stringArray[0];
        StartDate.setText(temp);

        Toast.makeText(this, stringArray[0], Toast.LENGTH_SHORT).show();


        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button2.setVisibility(View.VISIBLE);

                Hotelname.setEnabled(true);
                StartDate.setEnabled(true);
                Roomtype.setEnabled(true);
                Roomnumber.setEnabled(true);
                RoomOccupiedStatus.setEnabled(true);
                RoomAvailableStatus.setEnabled(true);
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