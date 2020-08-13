package com.cse5324.projecthotel;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerProfile extends AppCompatActivity {
    String[] Role;
    String role = "";
    Button update;
    DatabaseHelper db;
    String username = "",password = "",firstName = "",lastName = "", phone = "",email = "",Address = "",city = "", state = "", zipcode = "",creditCardNumber = "",
            creditCardExpiry = "",roles = "";
    EditText Username,Password,FirstName,LastName,Phone,Email,StreetAddress,City,State,ZipCode,CreditCardNumber,CreditCardExpiry,Roles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_profile);

        getSupportActionBar().setTitle("Manager Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //go back button

        Role = getSupportActionBar().getTitle().toString().split(" ");

        role =  Role[0];

        db = new DatabaseHelper(this);

        if(role.matches("Admin"))
        {
            role = "a";
        }
        else if(role.matches("Manager"))
        {
            role = "m";
        }
        else
        {
            role = "g";
        }


        Username = (EditText)findViewById(R.id.register_username);
        Password = (EditText)findViewById(R.id.register_password);
        FirstName = (EditText)findViewById(R.id.register_firstname);
        LastName = (EditText)findViewById(R.id.register_lastname);
        Phone = (EditText)findViewById(R.id.register_phone);
        Email = (EditText)findViewById(R.id.register_email);
        StreetAddress = (EditText)findViewById(R.id.register_address);
        City = (EditText)findViewById(R.id.register_city);
        State = (EditText)findViewById(R.id.register_state);
        ZipCode = (EditText)findViewById(R.id.register_zipcode);
        CreditCardNumber=(EditText)findViewById(R.id.register_credit_card);
        CreditCardExpiry =(EditText)findViewById(R.id.register_cc_expiry);
        Roles = (EditText)findViewById(R.id.register_Role);


        Cursor sd = db.getSelectedUserDetails(role);

        if (sd.moveToFirst()) {
            while (!sd.isAfterLast())
            {
                Username.setText(sd.getString(sd.getColumnIndex("USERNAME")));
                Password.setText(sd.getString(sd.getColumnIndex("PASSWORD")));
                FirstName.setText(sd.getString(sd.getColumnIndex("FIRSTNAME")));
                LastName.setText(sd.getString(sd.getColumnIndex("LASTNAME")));
                Phone.setText(sd.getString(sd.getColumnIndex("PHONE")));
                Email.setText(sd.getString(sd.getColumnIndex("EMAIL")));
                StreetAddress.setText(sd.getString(sd.getColumnIndex("ADDRESS")));
                City.setText(sd.getString(sd.getColumnIndex("CITY")));
                State.setText(sd.getString(sd.getColumnIndex("STATE")));
                ZipCode.setText(sd.getString(sd.getColumnIndex("ZIPCODE")));
                CreditCardNumber.setText(sd.getString(sd.getColumnIndex("CREDITCARDNO")));
                CreditCardExpiry.setText(sd.getString(sd.getColumnIndex("CREDITCARDEXPIRY")));
                Roles.setText(role);
                sd.moveToNext();
            }
        }

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
