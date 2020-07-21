package com.cse5324.projecthotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

/*
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
*/

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText userName, passWord, firstName, lastName, phone, email, address, city, state, zipcode, creditCardno, creditExpiry;
    String suserName, spassWord, sfirstName, slastName, sphone, semail, saddress, scity, sState, sZipCode, screditCardno, sCreditExpiry, sRole;
    Boolean Check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        db = new DatabaseHelper(this);

        getSupportActionBar().setTitle("Registration"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void registerCheck(View view)
    {
        userName = (EditText) findViewById(R.id.register_username);
        passWord = (EditText) findViewById(R.id.register_password);
        firstName = (EditText) findViewById(R.id.register_firstname);
        lastName = (EditText) findViewById(R.id.register_lastname);
        phone = (EditText) findViewById(R.id.register_phone);
        email = (EditText) findViewById(R.id.register_email);
        address = (EditText) findViewById(R.id.register_address);
        city = (EditText) findViewById(R.id.register_city);
        state = (EditText) findViewById(R.id.register_state);
        zipcode = (EditText) findViewById(R.id.register_zipcode);
        creditCardno = (EditText) findViewById(R.id.register_credit_card);
        creditExpiry = (EditText) findViewById(R.id.register_cc_expiry);

        Check = true;   //Important to add here, so registration works if user edits back to correct format
        suserName = userName.getText().toString().trim();

        if (suserName.matches(""))
        {
            userName.setError("Username field cannot be empty");
            Check = false;
        }

        spassWord = passWord.getText().toString().trim();

        if (spassWord.matches(""))
        {
            passWord.setError("Password field cannot be empty");
            Check = false;
        }


        sfirstName = firstName.getText().toString().trim();

        if (sfirstName.matches(""))
        {
            firstName.setError("FirstName field cannot be empty");
            Check = false;
        }


        slastName = lastName.getText().toString().trim();

        if (slastName.matches(""))
        {
            lastName.setError("LastName field cannot be empty");
            Check = false;
        }


        sphone = phone.getText().toString().trim();

        if (sphone.matches(""))
        {
            phone.setError("PhoneNumber field cannot be empty");
            Check = false;
        }


        semail = email.getText().toString().trim();

        if (semail.matches(""))
        {
            email.setError("Email field cannot be empty");
            Check = false;
        }


        saddress = address.getText().toString().trim();

        if (saddress.matches(""))
        {
            address.setError("Address field cannot be empty");
            Check = false;
        }


        scity = city.getText().toString().trim();

        if (scity.matches(""))
        {
            city.setError("City field cannot be empty");
            Check = false;
        }


        sState = state.getText().toString().trim();

        if (sState.matches(""))
        {
            state.setError("State field cannot be empty");
            Check = false;
        }


        sZipCode = zipcode.getText().toString().trim();

        if (sZipCode.matches(""))
        {
            zipcode.setError("Zipcode field cannot be empty");
            Check = false;
        }


        screditCardno = creditCardno.getText().toString().trim();

        if (screditCardno.matches(""))
        {
            creditCardno.setError("Credit Card Number cannot be empty");
            Check = false;
        }


        sCreditExpiry = creditExpiry.getText().toString();
        String p = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

        if (!Pattern.matches(p, sCreditExpiry))
        {
            creditExpiry.setError("Use the Valid date format given");
            Check = false;
        }

            /////////////////////
            boolean res = db.insertData(suserName,spassWord,sfirstName,slastName,sphone,semail,saddress,scity,sState,sZipCode,screditCardno,sCreditExpiry,"g");

            if(res == true)
            {
                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainAppScreenActivity.class));
            }
            else
            {
                Toast.makeText(RegisterActivity.this, "Error Occurred in Insertion", Toast.LENGTH_SHORT).show();
            }

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