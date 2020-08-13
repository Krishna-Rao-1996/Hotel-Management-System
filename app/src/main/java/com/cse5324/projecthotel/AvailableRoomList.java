package com.cse5324.projecthotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AvailableRoomList extends AppCompatActivity {
    String date="",time="",hotel="",activity="",hotelid = "";
    private ArrayList<HashMap<String, String>> list;
    ListView listContent;
    hotelDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);


        getSupportActionBar().setTitle("Reservation List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listContent = (ListView)findViewById(R.id.thelist);

        db = new hotelDatabase(this);

        hotel = getIntent().getExtras().getString("hotelname");
        date = getIntent().getExtras().getString("date");

        if(hotel.matches("Maverick"))
        {
            hotelid = "1";
        }
        else if(hotel.matches("Liberty"))
        {
            hotelid = "2";
        }
        else if(hotel.matches("Ranger"))
        {
            hotelid = "3";
        }
        else if(hotel.matches("Shard"))
        {
            hotelid = "4";
        }
        else
        {
            hotelid = "5";
        }

        Cursor sd = db.getListofAvailableRooms(hotelid);

        list=new ArrayList<HashMap<String,String>>();

        while (sd.moveToNext())
        {
            HashMap<String,String> temp = new HashMap<String, String>();
            temp.put("StartDate",date);
            temp.put("HotelName",hotel);
            temp.put("room_no",sd.getString(sd.getColumnIndex("room_no")));
            temp.put("RoomType",sd.getString(sd.getColumnIndex("roomType")));
            if(sd.getString(sd.getColumnIndex("roomType")).matches("Standard"))
            {
                temp.put("TotalPrice","80");
            }
            else if(sd.getString(sd.getColumnIndex("roomType")).matches("Deluxe"))
            {
                temp.put("TotalPrice","100");
            }
            else
            {
                temp.put("TotalPrice","120");
            }
            list.add(temp);
            AvailableRoomViewAdapter adapter=new AvailableRoomViewAdapter(this, list);
            listContent.setAdapter(adapter);
        }

        View header = (View)getLayoutInflater().inflate(R.layout.availableroom_layout,null);
        listContent.addHeaderView(header);
        //arrayAdapter = new ArrayAdapter<HashMap<String,String>>(this, R.layout.display_reservation_layouts,R.id.StartDate ,list);
        //listContent.setAdapter(arrayAdapter);
        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String text = parent.getItemAtPosition(position).toString();

                String[] nums = text.split(",");

                Intent myIntent = new Intent(AvailableRoomList.this, ViewSelectedRoom.class);
                myIntent.putExtra("listselected",nums);
                myIntent.putExtra("AvailableActivity","Available");
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
