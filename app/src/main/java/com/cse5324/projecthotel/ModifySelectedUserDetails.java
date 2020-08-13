package com.cse5324.projecthotel;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModifySelectedUserDetails extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;
    Button modifyUserDetailsBtn;
    EditText usernameEt, passwordEt, lastNameEt, firstNameEt, phoneEt, emailEt, addressEt, cityEt, stateEt, zipEt;
    String usernameIVal, passwordIVal, lastNameIVal, firstNameIVal, phoneIVal, emailIVal, addressIVal, cityIVal, stateIVal, zipIVal;
    String usernameNewVal, passwordNewVal, lastNameNewVal, firstNameNewVal, phoneNewVal, emailNewVal, addressNewVal, cityNewVal, stateNewVal, zipNewVal;
    String usernameOriginalVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_user_details_screen);

        getSupportActionBar().setTitle("Modify User Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        modifyUserDetailsBtn = findViewById(R.id.modify_user_details_btn);

        usernameEt = findViewById(R.id.modify_username);
        passwordEt = findViewById(R.id.modify_password);
        lastNameEt = findViewById(R.id.modify_lastname);
        firstNameEt = findViewById(R.id.modify_firstname);
        phoneEt = findViewById(R.id.modify_phone);
        emailEt = findViewById(R.id.modify_email);
        addressEt = findViewById(R.id.modify_address);
        cityEt = findViewById(R.id.modify_city);
        stateEt = findViewById(R.id.modify_state);
        zipEt = findViewById(R.id.modify_zip);

        //Storing intent values
        Intent intent = getIntent();
        usernameIVal = intent.getStringExtra("username_key");
        usernameOriginalVal = usernameIVal;
        passwordIVal = intent.getStringExtra("password_key");
        lastNameIVal = intent.getStringExtra("lastname_key");
        firstNameIVal = intent.getStringExtra("firstname_key");
        phoneIVal = intent.getStringExtra("phone_key");
        emailIVal = intent.getStringExtra("email_key");
        addressIVal = intent.getStringExtra("address_key");
        cityIVal = intent.getStringExtra("city_key");
        stateIVal = intent.getStringExtra("state_key");
        zipIVal = intent.getStringExtra("zip_key");

        //setting intent values to edittexts
        usernameEt.setText(usernameIVal);
        passwordEt.setText(passwordIVal);
        lastNameEt.setText(lastNameIVal);
        firstNameEt.setText(firstNameIVal);
        phoneEt.setText(phoneIVal);
        emailEt.setText(emailIVal);
        addressEt.setText(addressIVal);
        cityEt.setText(cityIVal);
        stateEt.setText(stateIVal);
        zipEt.setText(zipIVal);

        modifyUserDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameNewVal = usernameEt.getText().toString();
                passwordNewVal = passwordEt.getText().toString();
                lastNameNewVal = lastNameEt.getText().toString();
                firstNameNewVal = firstNameEt.getText().toString();
                phoneNewVal = phoneEt.getText().toString();
                emailNewVal = emailEt.getText().toString();
                addressNewVal = addressEt.getText().toString();
                cityNewVal = cityEt.getText().toString();
                stateNewVal = stateEt.getText().toString();
                zipNewVal = zipEt.getText().toString();

                databaseHelper = new DatabaseHelper(getApplicationContext());
                databaseHelper.modifyUserDetails(usernameNewVal, passwordNewVal, lastNameNewVal, firstNameNewVal,
                        phoneNewVal, emailNewVal, addressNewVal, cityNewVal, stateNewVal, zipNewVal, usernameOriginalVal);

                Intent intent = new Intent(ModifySelectedUserDetails.this, ViewSelectedUserDetails.class);
                intent.putExtra("username_key", usernameNewVal);
                startActivity(intent);

                //startActivity(new Intent(ModifySelectedUserDetails.this, ViewSelectedUserDetails.class));
                Toast.makeText(getApplicationContext(), "User Details Modified", Toast.LENGTH_SHORT).show();

            }
        });


        //Logout
        final Button logoutButton = findViewById(R.id.modifyUserDetailsPageLogoutBtn);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(ModifySelectedUserDetails.this, MainAppScreenActivity.class));
            }
        });

    }


    //go back button to work
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
