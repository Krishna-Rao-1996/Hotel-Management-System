package com.cse5324.projecthotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class AdminProfile extends AppCompatActivity {
    String[] Role;
    String role = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_profile);

        getSupportActionBar().setTitle("Admin Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //go back button

        Role = getSupportActionBar().getTitle().toString().split(" ");

        role =  Role[0];
    }
    //go back button to work
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
