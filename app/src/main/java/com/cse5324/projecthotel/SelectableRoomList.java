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

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

public class SelectableRoomList extends AppCompatActivity {
    String Roomno="",hotelid="";
    private ArrayList<HashMap<String, String>> list;
    private ArrayList<HashMap<String, String>> list1;
    ListView listContent;
    hotelDatabase db;
    ArrayAdapter<HashMap<String, String>> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectable_room_list);


        getSupportActionBar().setTitle("Search Room Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listContent = (ListView)findViewById(R.id.thelist);

        Roomno = getIntent().getExtras().getString("Roomno");
        hotelid = getIntent().getExtras().getString("hotelid");

        db = new hotelDatabase(this);

        list=new ArrayList<HashMap<String,String>>();
        list1 = new ArrayList<HashMap<String, String>>();

        int roomnum = Integer.parseInt(Roomno);

        Date myDate = new Date();

        SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
        String strDate = sm.format(myDate);

        Cursor cs = db.getBasicRoomDetails(hotelid,Roomno);

        HashMap<String,String> temp=new HashMap<String, String>();

        int theName = cs.getCount();

        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {

                String hotelid  = cs.getString(cs.getColumnIndex("hotel_id"));

                if(hotelid.matches("1"))
                {
                    temp.put("HotelName","Maverick");
                }
                else if(hotelid.matches("2"))
                {
                    temp.put("HotelName","Liberty");
                }
                else if(hotelid.matches("3"))
                {
                    temp.put("HotelName","Ranger");
                }
                else if(hotelid.matches("4"))
                {
                    temp.put("HotelName","Shard");
                }
                else
                {
                    temp.put("HotelName","Williams");
                }

                temp.put("StartDate",strDate);
                temp.put("RoomType",cs.getString(cs.getColumnIndex("roomType")));
                temp.put("RoomNumber",Roomno);
                list.add(temp);

                temp.put("RoomAvailableStatus",cs.getString(cs.getColumnIndex("status")));

                if(cs.getString(cs.getColumnIndex("roomType")).matches("Standard"))
                {
                    temp.put("RoomRate","80");
                }
                else if(cs.getString(cs.getColumnIndex("roomType")).matches("Deluxe"))
                {
                    temp.put("RoomRate","100");
                }
                else
                {
                    temp.put("RoomRate","120");
                }

                if(cs.getString(cs.getColumnIndex("status")).matches("available"))
                {
                    temp.put("RoomOccupiedStatus","unoccupied");
                }
                else
                {
                    temp.put("RoomOccupiedStatus","occupied");
                }
                list1.add(temp);
                cs.moveToNext();
            }
        }

        RoomListViewAdapter adapter=new RoomListViewAdapter(this, list);
        View header = (View)getLayoutInflater().inflate(R.layout.display_room_list,null);
        listContent.addHeaderView(header);
        listContent.setAdapter(adapter);

        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                HashMap<String, String> m = list1.get(0);//it will get the first HashMap Stored in array list

                String strArr[] = new String[m.size()];
                int i = 0;
                for (HashMap<String, String> hash : list1) {
                    for (String current : hash.values()) {
                        strArr[i] = current;
                        i++;
                    }
                }

                Intent myIntent = new Intent(SelectableRoomList.this, ViewSelectedRoom.class);
                myIntent.putExtra("listselected",strArr);
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
