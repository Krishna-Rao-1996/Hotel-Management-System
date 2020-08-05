package com.cse5324.projecthotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GuestScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_screen);

        getSupportActionBar().setTitle("Guest Home");

    //Unhide when you unhide spinnerG
   /*     Spinner spinner = (Spinner) findViewById(R.id.spinnerG);
        //To get values from the array created in strings.xml and send to the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.guest_functions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    */
        //Logout
        final Button button= findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(GuestScreen.this, MainAppScreenActivity.class));
            }
        });

        final Button b1=findViewById(R.id.profile);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Profile page
                //Toast.makeText(GuestScreen.this, "My Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(GuestScreen.this, GuestProfile.class));
            }
        });

        final Button b2=findViewById(R.id.request);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Request Reservation page
                //Toast.makeText(GuestScreen.this, "Request Hotel Reservations", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(GuestScreen.this, ReservationSummary.class));
            }
        });

        final Button b3=findViewById(R.id.confirmReserve);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Summary page
                //Toast.makeText(GuestScreen.this, "View Reservations Summary", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GuestScreen.this, ViewReservations.class);
                intent.putExtra("activity","GuestScreen");
                startActivity(intent);
            }
        });

      /*  final Button b4=findViewById(R.id.pending);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Pending Reservations page
                //Toast.makeText(GuestScreen.this, "View Pending Reservations", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(GuestScreen.this, PendingReservations.class));
            }
        }); */
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text= parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        String lower=text.toLowerCase();
        Intent intent;
        if (lower.contains("profile")) {
            //startActivity(new Intent(this, ___.class));
            Toast.makeText(parent.getContext(), lower, Toast.LENGTH_SHORT).show();
        }
        else if (lower.contains("reservation")) {
            //startActivity(new Intent(this, ___.class));
            Toast.makeText(parent.getContext(), lower, Toast.LENGTH_SHORT).show();
        }
        else if (lower.contains("summary")) {
            //startActivity(new Intent(this, ___.class));
        }
        else if (lower.contains("pending")) {
            //startActivity(new Intent(this, ___.class));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
