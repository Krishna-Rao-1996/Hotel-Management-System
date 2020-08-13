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
    private Button home;
    hotelDatabase db;
    String roomStatus="",roomStat = "",roomnumber = "",roomOccupiedstat = "",activity = "";
    EditText Hotelname,StartDate,Roomtype,Roomnumber,RoomOccupiedStatus,RoomAvailableStatus,RoomRate;
    String [] stringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_room);

        getSupportActionBar().setTitle("Modify Selected Room");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Hotelname = (EditText)findViewById(R.id.hotelName);

        db = new hotelDatabase(this);

        Intent intent = getIntent();
        stringArray = intent.getStringArrayExtra("listselected");
        activity = intent.getExtras().getString("AvailableActivity");


        Hotelname = (EditText)findViewById(R.id.hotelName);
        StartDate = (EditText)findViewById(R.id.StartDate);
        Roomtype = (EditText)findViewById(R.id.roomType);
        Roomnumber = (EditText)findViewById(R.id.RoomNumber);
        RoomOccupiedStatus = (EditText)findViewById(R.id.roomOcuupiedStatus);
        RoomAvailableStatus = (EditText)findViewById(R.id.roomAvailableStatus);
        RoomRate = (EditText)findViewById(R.id.roomRate);


        if(activity == null)
        {
            StartDate.setText(stringArray[0]);
        }
        else
        {
            String[] a = stringArray[0].split("=");
            String as = a[1];

            StartDate.setText(as);
        }
        if(activity == null)
        {
            Hotelname.setText(stringArray[4]);
        }
        else
        {
            String[] a = stringArray[4].split("=");
            String as = a[1];
            as =  as.substring(0, as.length() - 1);

            Hotelname.setText(as);
        }
        if(activity == null)
        {
            Roomtype.setText(stringArray[1]);
        }
        else
        {
            String[] a = stringArray[1].split("=");
            String as = a[1];
            Roomtype.setText(as);
        }
        if(activity == null)
        {
            RoomOccupiedStatus.setText(stringArray[3]);
        }
        else
        {
            RoomOccupiedStatus.setText("unoccupied");
        }
        if(activity == null)
        {
            RoomAvailableStatus.setText(stringArray[2]);
        }
        else
        {
            RoomAvailableStatus.setText("available");
        }
        if(activity == null)
        {
            Roomnumber.setText(stringArray[5]);
        }
        else
        {
            String[] a = stringArray[3].split("=");
            String as = a[1];
            Roomnumber.setText(as);
        }
        if(activity == null)
        {
            RoomRate.setText(stringArray[6]);
        }
        else
        {
            String[] a = stringArray[2].split("=");
            String as = a[1];
            RoomRate.setText(as);
        }


        roomStatus = RoomAvailableStatus.getText().toString();
        roomnumber = Roomnumber.getText().toString();
        roomOccupiedstat = RoomOccupiedStatus.getText().toString();

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        home = findViewById(R.id.home);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                roomStat = RoomAvailableStatus.getText().toString();
                boolean res = db.updateRoomStatus(roomStat,roomnumber);
                if(roomStat == "available")
                {
                    RoomOccupiedStatus.setText("unoccupied");
                }
                else
                {
                    RoomOccupiedStatus.setText("occupied");
                }
                Toast.makeText(ViewSelectedRoom.this, "Successful!", Toast.LENGTH_SHORT).show();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getSupportActionBar().setTitle("Modify Selected Room");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                button2.setVisibility(View.VISIBLE);
                RoomAvailableStatus.setEnabled(true);
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ViewSelectedRoom.this, ManagerScreen.class);
                startActivity(myIntent);
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