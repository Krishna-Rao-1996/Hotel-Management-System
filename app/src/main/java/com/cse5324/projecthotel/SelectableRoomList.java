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

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SelectableRoomList extends AppCompatActivity {
    String Roomno="",hotelname="";
    private ArrayList<HashMap<String, String>> list;
    private ArrayList<HashMap<String, String>> list1;
    ListView listContent;
    DatabaseHelper db;
    ArrayAdapter<HashMap<String, String>> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectable_room_list);


        getSupportActionBar().setTitle("Search Room Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listContent = (ListView)findViewById(R.id.thelist);

        Roomno = getIntent().getExtras().getString("Roomno");
        hotelname = getIntent().getExtras().getString("hotelname");

        db = new DatabaseHelper(this);

        list=new ArrayList<HashMap<String,String>>();
        list1 = new ArrayList<HashMap<String, String>>();

        int roomnum = Integer.parseInt(Roomno);

        Date myDate = new Date();

        SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
        String strDate = sm.format(myDate);

        if(hotelname.matches("Maverick"))
        {
            if(roomnum >= 101 && roomnum <=125 || roomnum >=201 && roomnum <=225 || roomnum >=301 && roomnum <=325 || roomnum >=401 && roomnum <=413)
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Maverick");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Standard");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","80");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
            else if(roomnum>=414 && roomnum<=421)
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Maverick");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Deluxe");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","100");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
            else
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Maverick");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Suite");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","120");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
        }
        else if(hotelname.matches("Liberty"))
        {
            if(roomnum >= 101 && roomnum <=125 || roomnum >=201 && roomnum <=225 || roomnum >=301 && roomnum <=325 || roomnum >=401 && roomnum <=413)
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Liberty");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Standard");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","80");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
            else if(roomnum>=414 && roomnum<=421)
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Liberty");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Deluxe");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","100");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
            else
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Liberty");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Suite");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","120");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
        }
        else if(hotelname.matches("Ranger"))
        {
            if(roomnum >= 101 && roomnum <=125 || roomnum >=201 && roomnum <=225 || roomnum >=301 && roomnum <=325 || roomnum >=401 && roomnum <=413)
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Ranger");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Standard");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","80");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
            else if(roomnum>=414 && roomnum<=421)
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Ranger");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Deluxe");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","100");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
            else
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Ranger");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Suite");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","120");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
        }
        else if(hotelname.matches("Shard"))
        {
            if(roomnum >= 101 && roomnum <=125 || roomnum >=201 && roomnum <=225 || roomnum >=301 && roomnum <=325 || roomnum >=401 && roomnum <=413)
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Shard");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Standard");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","80");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
            else if(roomnum>=414 && roomnum<=421)
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Shard");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Deluxe");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","100");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
            else
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Shard");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Suite");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","120");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
        }
        else if(hotelname.matches("Williams"))
        {
            if(roomnum >= 101 && roomnum <=125 || roomnum >=201 && roomnum <=225 || roomnum >=301 && roomnum <=325 || roomnum >=401 && roomnum <=413)
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Williams");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Standard");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","80");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
            else if(roomnum>=414 && roomnum<=421)
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Williams");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Deluxe");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","100");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
            }
            else
            {
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("HotelName","Williams");
                temp.put("StartDate",strDate);
                temp.put("RoomType","Suite");
                temp.put("RoomNumber",Roomno);
                list.add(temp);
                temp.put("RoomRate","120");
                temp.put("RoomOccupiedStatus","False");
                temp.put("RoomAvailableStatus","True");
                list1.add(temp);
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
