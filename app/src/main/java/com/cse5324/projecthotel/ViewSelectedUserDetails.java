package com.cse5324.projecthotel;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewSelectedUserDetails extends AppCompatActivity implements RemoveUserDialog.DialogListener {

    DatabaseHelper databaseHelper;
    Cursor cursor;
    View linearLayout;
    TableLayout mainTable;
    String userName, password, lastName, firstName, phone, email, address, city, state, zip, roleInitial, role;
    TextView usernameTv, passwordTv, lastNameTv, firstNameTv, phoneTv, emailTv, addressTv, cityTv, stateTv, zipTv, roleTv;
    TextView usernameDbValTv, passwordDbValTv, lastNameDbValTv, firstNameDbValTv, phoneDbValTv, emailDbValTv, addressDbValTv, cityDbValTv, stateDbValTv, zipDbValTv, roleDbValTv;
    TableRow usernameRow, passwordRow, lastNameRow, firstNameRow, phoneRow, emailRow, addressRow, cityRow, stateRow, zipRow, roleRow;
    int colIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_user_details);

        getSupportActionBar().setTitle("View Selected User Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        linearLayout = findViewById(R.id.selectedUserDetailsPage);

        mainTable = new TableLayout(this);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        mainTable.setLayoutParams(param);
        Drawable tableBg = getResources().getDrawable(R.drawable.border);
        mainTable.setBackground(tableBg);
        param.setMargins(5, 5, 5, 5);


        Intent intent = getIntent();
        final String userNameValue = intent.getStringExtra("username_key");

        databaseHelper = new DatabaseHelper(getApplicationContext());
        cursor = databaseHelper.getSelectedUserDetails(userNameValue);

        while (cursor.moveToNext()) {

            colIndex = cursor.getColumnIndexOrThrow("USERNAME");
            userName = cursor.getString(colIndex);

            colIndex = cursor.getColumnIndexOrThrow("PASSWORD");
            password = cursor.getString(colIndex);

            colIndex = cursor.getColumnIndexOrThrow("LASTNAME");
            lastName = cursor.getString(colIndex);

            colIndex = cursor.getColumnIndexOrThrow("FIRSTNAME");
            firstName = cursor.getString(colIndex);

            colIndex = cursor.getColumnIndexOrThrow("PHONE");
            phone = cursor.getString(colIndex);

            colIndex = cursor.getColumnIndexOrThrow("EMAIL");
            email = cursor.getString(colIndex);

            colIndex = cursor.getColumnIndexOrThrow("ADDRESS");
            address = cursor.getString(colIndex);

            colIndex = cursor.getColumnIndexOrThrow("CITY");
            city = cursor.getString(colIndex);

            colIndex = cursor.getColumnIndexOrThrow("STATE");
            state = cursor.getString(colIndex);

            colIndex = cursor.getColumnIndexOrThrow("ZIPCODE");
            zip = cursor.getString(colIndex);

            colIndex = cursor.getColumnIndexOrThrow("ROLE");
            roleInitial = cursor.getString(colIndex);


            if (roleInitial.contains("g")) {
                role = "Guest";
            } else if (roleInitial.contains("m")) {
                role = "Manager";
            } else {
                role = "Admin";
            }


            //Display Username
            usernameRow = new TableRow(this);
            usernameTv = new TextView(this);
            usernameTv.setTypeface(null, Typeface.BOLD);
            usernameTv.setText("Username: ");
            usernameTv.setTextSize(15);

            usernameDbValTv = new TextView(this);
            usernameDbValTv.setText(userName);        //get date from db
            usernameDbValTv.setTextSize(15);
            usernameRow.addView(usernameTv);
            usernameRow.addView(usernameDbValTv);
            mainTable.addView(usernameRow);

            //Display Password
            passwordRow = new TableRow(this);
            passwordTv = new TextView(this);
            passwordTv.setTypeface(null, Typeface.BOLD);
            passwordTv.setText("Password: ");
            passwordTv.setTextSize(15);

            passwordDbValTv = new TextView(this);
            passwordDbValTv.setText(password);        //get date from db
            passwordDbValTv.setTextSize(15);
            passwordRow.addView(passwordTv);
            passwordRow.addView(passwordDbValTv);
            mainTable.addView(passwordRow);

            //Display Last Name
            lastNameRow = new TableRow(this);
            lastNameTv = new TextView(this);
            lastNameTv.setTypeface(null, Typeface.BOLD);
            lastNameTv.setText("Last Name: ");
            lastNameTv.setTextSize(15);

            lastNameDbValTv = new TextView(this);
            lastNameDbValTv.setText(lastName);        //get date from db
            lastNameDbValTv.setTextSize(15);
            lastNameRow.addView(lastNameTv);
            lastNameRow.addView(lastNameDbValTv);
            mainTable.addView(lastNameRow);

            //Display First Name
            firstNameRow = new TableRow(this);
            firstNameTv = new TextView(this);
            firstNameTv.setTypeface(null, Typeface.BOLD);
            firstNameTv.setText("First Name: ");
            firstNameTv.setTextSize(15);

            firstNameDbValTv = new TextView(this);
            firstNameDbValTv.setText(firstName);        //get date from db
            firstNameDbValTv.setTextSize(15);
            firstNameRow.addView(firstNameTv);
            firstNameRow.addView(firstNameDbValTv);
            mainTable.addView(firstNameRow);

            //Display Phone
            phoneRow = new TableRow(this);
            phoneTv = new TextView(this);
            phoneTv.setTypeface(null, Typeface.BOLD);
            phoneTv.setText("Phone: ");
            phoneTv.setTextSize(15);

            phoneDbValTv = new TextView(this);
            phoneDbValTv.setText(phone);        //get date from db
            phoneDbValTv.setTextSize(15);
            phoneRow.addView(phoneTv);
            phoneRow.addView(phoneDbValTv);
            mainTable.addView(phoneRow);

            //Display Email
            emailRow = new TableRow(this);
            emailTv = new TextView(this);
            emailTv.setTypeface(null, Typeface.BOLD);
            emailTv.setText("Email: ");
            emailTv.setTextSize(15);

            emailDbValTv = new TextView(this);
            emailDbValTv.setText(email);        //get date from db
            emailDbValTv.setTextSize(15);
            emailRow.addView(emailTv);
            emailRow.addView(emailDbValTv);
            mainTable.addView(emailRow);

            //Display Address
            addressRow = new TableRow(this);
            addressTv = new TextView(this);
            addressTv.setTypeface(null, Typeface.BOLD);
            addressTv.setText("Street Address: ");
            addressTv.setTextSize(15);

            addressDbValTv = new TextView(this);
            addressDbValTv.setText(address);        //get date from db
            addressDbValTv.setTextSize(15);
            addressRow.addView(addressTv);
            addressRow.addView(addressDbValTv);
            mainTable.addView(addressRow);

            //Display City
            cityRow = new TableRow(this);
            cityTv = new TextView(this);
            cityTv.setTypeface(null, Typeface.BOLD);
            cityTv.setText("City: ");
            cityTv.setTextSize(15);

            cityDbValTv = new TextView(this);
            cityDbValTv.setText(city);        //get date from db
            cityDbValTv.setTextSize(15);
            cityRow.addView(cityTv);
            cityRow.addView(cityDbValTv);
            mainTable.addView(cityRow);


            //Display State
            stateRow = new TableRow(this);
            stateTv = new TextView(this);
            stateTv.setTypeface(null, Typeface.BOLD);
            stateTv.setText("State: ");
            stateTv.setTextSize(15);

            stateDbValTv = new TextView(this);
            stateDbValTv.setText(state);        //get date from db
            stateDbValTv.setTextSize(15);
            stateRow.addView(stateTv);
            stateRow.addView(stateDbValTv);
            mainTable.addView(stateRow);

            //Display Zip
            zipRow = new TableRow(this);
            zipTv = new TextView(this);
            zipTv.setTypeface(null, Typeface.BOLD);
            zipTv.setText("Zip Code: ");
            zipTv.setTextSize(15);

            zipDbValTv = new TextView(this);
            zipDbValTv.setText(zip);        //get date from db
            zipDbValTv.setTextSize(15);
            zipRow.addView(zipTv);
            zipRow.addView(zipDbValTv);
            mainTable.addView(zipRow);

            //Display Role
            roleRow = new TableRow(this);
            roleTv = new TextView(this);
            roleTv.setTypeface(null, Typeface.BOLD);
            roleTv.setText("Role: ");
            roleTv.setTextSize(15);

            roleDbValTv = new TextView(this);
            roleDbValTv.setText(role);        //get date from db
            roleDbValTv.setTextSize(15);
            roleRow.addView(roleTv);
            roleRow.addView(roleDbValTv);
            mainTable.addView(roleRow);

        }

        ((LinearLayout) linearLayout).addView(mainTable);

        final Button modifyUserBtn = new Button(this);
        modifyUserBtn.setId(R.id.modifyUserBtn);
        modifyUserBtn.setText("Modify User Details");
        modifyUserBtn.setBackgroundColor(Color.parseColor("#2C1EC6"));
        modifyUserBtn.setTextColor(Color.WHITE);

        final Button removeUserBtn = new Button(this);
        removeUserBtn.setId(R.id.removeUserBtn);
        removeUserBtn.setText("Remove User");
        removeUserBtn.setBackgroundColor(Color.parseColor("#2C1EC6"));
        removeUserBtn.setTextColor(Color.WHITE);

        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        btnParams.setMargins(10, 10, 10, 10);

        modifyUserBtn.setLayoutParams(btnParams);
        removeUserBtn.setLayoutParams(btnParams);

        ((LinearLayout) linearLayout).addView(modifyUserBtn);
        ((LinearLayout) linearLayout).addView(removeUserBtn);

        //opens remove user confirmation dialog box
        removeUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        //opens Modify Selected User Details Screen
        modifyUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSelectedUserDetails.this, ModifySelectedUserDetails.class);
                intent.putExtra("username_key", userName);
                intent.putExtra("password_key", password);
                intent.putExtra("lastname_key", lastName);
                intent.putExtra("firstname_key", firstName);
                intent.putExtra("phone_key", phone);
                intent.putExtra("email_key", email);
                intent.putExtra("address_key", address);
                intent.putExtra("city_key", city);
                intent.putExtra("state_key", state);
                intent.putExtra("zip_key", zip);
                startActivity(intent);
            }
        });


        //Logout
        final Button logoutBtn = findViewById(R.id.selectedUserDetailsPageLogoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(ViewSelectedUserDetails.this, MainAppScreenActivity.class));
            }
        });

    }

    public void openDialog() {
        RemoveUserDialog removeUserDialog = new RemoveUserDialog();
        removeUserDialog.show(getSupportFragmentManager(), "Remove User Dialog");
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

    @Override
    public void onNoClicked() {
        Toast.makeText(getApplicationContext(), "User Not Removed!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onYesClicked() {
        databaseHelper = new DatabaseHelper(getApplicationContext());
        int rows_deleted = databaseHelper.removeUser(userName);

        if (rows_deleted != 0) {
            startActivity(new Intent(ViewSelectedUserDetails.this, SearchUser.class));
            Toast.makeText(getApplicationContext(), "User Removed From Database", Toast.LENGTH_SHORT).show();

        }

    }
}
