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

public class ReservationList extends AppCompatActivity {
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

        Cursor sd = db.getListofReservations(hotelid);

        list=new ArrayList<HashMap<String,String>>();

        HashMap<String,String> temp = new HashMap<String, String>();

        if (sd.moveToFirst()) {
            while (!sd.isAfterLast())
            {
                temp.clear();
                temp.put("StartDate",date);
                temp.put("StartTime",sd.getString(sd.getColumnIndex("inTime")));
                temp.put("HotelName",hotel);
                temp.put("NumberofRooms",sd.getString(sd.getColumnIndex("numOfRooms")));
                temp.put("CheckinDate",sd.getString(sd.getColumnIndex("fromDate")));
                temp.put("CheckoutDate",sd.getString(sd.getColumnIndex("toDate")));
                temp.put("RoomType",sd.getString(sd.getColumnIndex("rType")));
                if(sd.getString(sd.getColumnIndex("rType")).matches("Standard"))
                {
                    temp.put("TotalPrice","80");
                }
                else if(sd.getString(sd.getColumnIndex("rType")).matches("Deluxe"))
                {
                    temp.put("TotalPrice","100");
                }
                else
                {
                    temp.put("TotalPrice","120");
                }
                list.add(temp);
                sd.moveToNext();
            }
        }

        ListViewAdapter adapter=new ListViewAdapter(this, list);
        View header = (View)getLayoutInflater().inflate(R.layout.row_layout_list,null);
        listContent.addHeaderView(header);
        listContent.setAdapter(adapter);
        //arrayAdapter = new ArrayAdapter<HashMap<String,String>>(this, R.layout.display_reservation_layouts,R.id.StartDate ,list);
        //listContent.setAdapter(arrayAdapter);
        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String text = parent.getItemAtPosition(position).toString();

                Intent myIntent = new Intent(ReservationList.this, SpecificReservation.class);
                myIntent.putExtra("listselected",text);
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
