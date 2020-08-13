package com.cse5324.projecthotel;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchUser extends AppCompatActivity {

    EditText searchUserET;
    Button searchUserBtn;
    DatabaseHelper databaseHelper;
    Cursor cursorData;
    View linearLayout;
    TableLayout mainTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_user);

        getSupportActionBar().setTitle("Search for User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //go back button

        searchUserET = findViewById(R.id.userSearchET);
        searchUserBtn = findViewById(R.id.searchUserBtn);

        linearLayout = findViewById(R.id.searchUserLayoutPage);


        searchUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchUserForAdmin(v);
            }
        });

        //Logout
        final Button logoutButton = findViewById(R.id.searchUserLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(SearchUser.this, MainAppScreenActivity.class));
            }
        });
    }


    public void searchUserForAdmin(View view) {

        String userLastNameValue = searchUserET.getText().toString();

        databaseHelper = new DatabaseHelper(getApplicationContext());
        cursorData = databaseHelper.searchUsersForAdmin(userLastNameValue);

        //Finding Users Found Count & displaying in Toast
        int userFoundCountInt = cursorData.getCount();
        String userFoundCount = Integer.toString(userFoundCountInt);
        String userFoundCountToastMsg = "Users Found: " + userFoundCount;

        if (cursorData.getCount() != 0) {
            Toast.makeText(this, userFoundCountToastMsg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show();
        }

        //While user data is present in Cursor
        while (cursorData.moveToNext()) {
            int colIndex;

            colIndex = cursorData.getColumnIndexOrThrow("USERNAME");
            final String userName = cursorData.getString(colIndex);

            colIndex = cursorData.getColumnIndexOrThrow("LASTNAME");
            String lastName = cursorData.getString(colIndex);

            colIndex = cursorData.getColumnIndexOrThrow("FIRSTNAME");
            String firstName = cursorData.getString(colIndex);

            colIndex = cursorData.getColumnIndexOrThrow("PHONE");
            String phone = cursorData.getString(colIndex);

            colIndex = cursorData.getColumnIndexOrThrow("EMAIL");
            String email = cursorData.getString(colIndex);


            //ll = new LinearLayout(this);

            //for (int i = 1; i <= userFoundCountInt; i++) {

            //Main Table Layout Structure
            mainTable = new TableLayout(this);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            mainTable.setLayoutParams(param);
            Drawable tableBg = getResources().getDrawable(R.drawable.border);
            mainTable.setBackground(tableBg);
            param.setMargins(5, 5, 5, 5);


//            mainRow.setBackgroundColor(Color.parseColor("#ffffff"));
//            mainRow.callOnClick();
//            TableLayout.LayoutParams params = new TableLayout.LayoutParams(
//                    TableLayout.LayoutParams.MATCH_PARENT,
//                    TableLayout.LayoutParams.MATCH_PARENT
//            );
//            params.setMargins(5, 3, 5, 3);
//            mainRow.setLayoutParams(params);
//            mainRow.setWeightSum(1f);

            //User Details Display Table
//            TableLayout userDetailsTable = new TableLayout(this);
//            TableRow.LayoutParams p = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT);
//            p.weight = 0.65f;
//            p.setMargins(5,5,5,0);
//            userDetailsTable.setLayoutParams(p);

            //Username Display Row
            TableRow usernameRow = new TableRow(this);
            TextView usernameTV = new TextView(this);
            usernameTV.setTypeface(null, Typeface.BOLD);
            usernameTV.setText("Username: ");
            usernameTV.setTextSize(15);

            TextView usernameDbValue = new TextView(this);
            usernameDbValue.setText(userName);        //get date from db
            usernameDbValue.setTextSize(15);
            usernameRow.addView(usernameTV);
            usernameRow.addView(usernameDbValue);
            mainTable.addView(usernameRow);

            //LastName Display Row
            TableRow lastNameRow = new TableRow(this);
            TextView lastNameTV = new TextView(this);
            lastNameTV.setTypeface(null, Typeface.BOLD);
            lastNameTV.setText("Last Name: ");
            lastNameTV.setTextSize(15);

            TextView lastNameDbValue = new TextView(this);
            lastNameDbValue.setText(lastName);        //get date from db
            lastNameDbValue.setTextSize(15);
            lastNameRow.addView(lastNameTV);
            lastNameRow.addView(lastNameDbValue);
            mainTable.addView(lastNameRow);

            //FirstName Display Row
            TableRow firstNameRow = new TableRow(this);
            TextView firstNameTV = new TextView(this);
            firstNameTV.setTypeface(null, Typeface.BOLD);
            firstNameTV.setText("First Name: ");
            firstNameTV.setTextSize(15);

            TextView firstNameDbValue = new TextView(this);
            firstNameDbValue.setText(firstName);        //get date from db
            firstNameDbValue.setTextSize(15);
            firstNameRow.addView(firstNameTV);
            firstNameRow.addView(firstNameDbValue);
            mainTable.addView(firstNameRow);

            //Phone Display Row
            TableRow phoneRow = new TableRow(this);
            TextView phoneTV = new TextView(this);
            phoneTV.setTypeface(null, Typeface.BOLD);
            phoneTV.setText("Phone: ");
            phoneTV.setTextSize(15);

            TextView phoneDbValue = new TextView(this);
            phoneDbValue.setText(phone);        //get date from db
            phoneDbValue.setTextSize(15);
            phoneRow.addView(phoneTV);
            phoneRow.addView(phoneDbValue);
            mainTable.addView(phoneRow);

            //Email Display Row
            TableRow emailRow = new TableRow(this);
            TextView emailTV = new TextView(this);
            emailTV.setTypeface(null, Typeface.BOLD);
            emailTV.setText("Email: ");
            emailTV.setTextSize(15);

            TextView emailDbValue = new TextView(this);
            emailDbValue.setText(email);        //get date from db
            emailDbValue.setTextSize(15);
            emailRow.addView(emailTV);
            emailRow.addView(emailDbValue);
            mainTable.addView(emailRow);

            //mainTable.addView(userDetailsTable);
            //mainRow.addView(userDetailsTable);

            //ll.addView(mainTable);
            ((LinearLayout) linearLayout).addView(mainTable);

            mainTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SearchUser.this, ViewSelectedUserDetails.class);
                    intent.putExtra("username_key", userName);
                    startActivity(intent);
                }
            });


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