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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchRoom extends AppCompatActivity {

    Spinner spinner2;
    String hotelNames="",roomnu="",hotelid = "";
    EditText roomnumber;
    int roomno;
    private Button enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_room);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        roomnumber = (EditText)findViewById(R.id.editTextNumber);
        List<String> list = new ArrayList<String>();

        list.add("Maverick");
        list.add("Liberty");
        list.add("Ranger");
        list.add("Shard");
        list.add("Williams");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            public void onClick(View v)
            {

                roomnu = roomnumber.getText().toString();

                if(hotelNames == "Maverick")
                {
                    hotelid = "1";
                }
                else if(hotelNames == "Liberty")
                {
                    hotelid = "2";
                }
                else if(hotelNames == "Ranger")
                {
                    hotelid = "3";
                }
                else if(hotelNames == "Shard")
                {
                    hotelid = "4";
                }
                else
                {
                    hotelid = "5";
                }

                if(roomnu.matches(""))
                {
                    Toast.makeText(SearchRoom.this, "Room Number cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    roomno = Integer.parseInt(roomnu);

                    if(roomno >= 101 && roomno <=125 || roomno >=201 && roomno <=225 || roomno >=301 && roomno <=325 || roomno >=401 && roomno <=413 ||
                            roomno>=414 && roomno<=421 || roomno>=422 && roomno<=425)
                    {
                        Intent myIntent = new Intent(SearchRoom.this, SelectableRoomList.class);
                        myIntent.putExtra("Roomno",roomnu);
                        myIntent.putExtra("hotelid",hotelid);
                        startActivity(myIntent);
                    }
                    else
                    {
                        Toast.makeText(SearchRoom.this, "Enter Valid Room Number: Range - (101-125) (201-225) (301-325) (401-425)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        getSupportActionBar().setTitle("Search Room Filters");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //go back button
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


