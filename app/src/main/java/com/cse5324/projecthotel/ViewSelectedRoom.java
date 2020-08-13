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


        if(activity.equals("Available"))
        {
            String[] a = stringArray[0].split("=");
            String as = a[1];
            as =  as.substring(1);

            StartDate.setText(as);
        }
        else
        {
            StartDate.setText(stringArray[0]);
        }
        if(activity.equals("Available"))
        {
            String[] a = stringArray[0].split("=");
            String as = a[1];
            as =  as.substring(0, as.length() - 1);

            Hotelname.setText(as);
        }
        else
        {
            Hotelname.setText(stringArray[4]);
        }

        Roomtype.setText(stringArray[1]);
        if(activity.equals("Available"))
        {
            RoomOccupiedStatus.setText("unoccupied");
        }
        else
        {
            RoomOccupiedStatus.setText(stringArray[3]);
        }
        if(activity.equals("Available"))
        {
            RoomAvailableStatus.setText("available");
        }
        else
        {
            RoomAvailableStatus.setText(stringArray[2]);
        }
        if(activity.equals("Available"))
        {
            Roomnumber.setText(stringArray[3]);
        }
        else
        {
            Roomnumber.setText(stringArray[5]);
        }
        if(activity.equals("Available"))
        {
            RoomRate.setText(stringArray[2]);
        }
        else
        {
            RoomRate.setText(stringArray[6]);
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

                if(roomStatus.matches(roomStat) == false) {
                    roomStat = "unavailable";
                    RoomOccupiedStatus.setText("occupied");
                    boolean res = db.updateRoomStatus(roomStat,roomStat);
                    Toast.makeText(ViewSelectedRoom.this, "Successful!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ViewSelectedRoom.this, "No changes done to the table!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getSupportActionBar().setTitle("Modify Selected Room");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                button2.setVisibility(View.VISIBLE);
                RoomAvailableStatus.setEnabled(true);
                RoomRate.setEnabled(true);
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