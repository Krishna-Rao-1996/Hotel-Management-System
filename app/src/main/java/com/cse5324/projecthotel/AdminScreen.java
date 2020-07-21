package com.cse5324.projecthotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminScreen extends AppCompatActivity{     //implements AdapterView.OnItemSelectedListener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_screen);

        getSupportActionBar().setTitle("Admin Home"); // for set actionbar title

        //Logout
        final Button button= findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(AdminScreen.this, MainAppScreenActivity.class));
            }
        });

        //Functions
        final Button b1= findViewById(R.id.profileA);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Profile page
                startActivity(new Intent(AdminScreen.this, AdminProfile.class));
                //Toast.makeText(AdminScreen.this, "View Profile", Toast.LENGTH_SHORT).show();
            }
        });
        final Button b2= findViewById(R.id.searchU);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(AdminScreen.this, SearchUser.class));
                //Toast.makeText(AdminScreen.this, "Search for a User", Toast.LENGTH_SHORT).show();
            }
        });
    }
/* unhide for menu
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
        else if (lower.contains("search")) {
            //startActivity(new Intent(this, ___.class));
            Toast.makeText(parent.getContext(), lower, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
 */
}
