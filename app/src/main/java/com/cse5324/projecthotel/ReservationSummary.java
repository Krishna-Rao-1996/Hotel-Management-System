package com.cse5324.projecthotel;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReservationSummary extends AppCompatActivity {
    hotelDatabase hdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_reservations);

        getSupportActionBar().setTitle("View Reservation Summary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //What's the user id?
        Intent getI = getIntent();
        final String info = getI.getStringExtra("user_id");
        final String from=getI.getStringExtra("from");
        //Toast.makeText(ReservationSummary.this, "user_id="+info, Toast.LENGTH_SHORT).show();

        //Logout
        final Button button= findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(ReservationSummary.this, MainAppScreenActivity.class));
            }
        });
        //OpenMenu
        final Button btnMenu= findViewById(R.id.filterMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                Intent intent=new Intent(ReservationSummary.this, ReservationSummaryFilter.class);
                intent.putExtra("user_id", info);
                startActivity(intent);
            }
        });

        //Load Page contents
        View linearLayout = findViewById(R.id.page);
        ScrollView sv = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);

        //Structure
        TableLayout table = new TableLayout(this);
        table.setBackgroundColor(Color.parseColor("#000000"));
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        table.setLayoutParams(param);
        param.setMargins(5,5,5,5);

        hdb = new hotelDatabase(this);
        if(info!=null)
        {
            String date="";
            String time="";
            if(from!=null)
            {
                date=getI.getStringExtra("startDate");
                time=getI.getStringExtra("startTime");
            }
            Cursor cur=hdb.getReservation(Integer.parseInt(info), date, time);
            int count=cur.getCount();
            Log.i("where: ", Integer.toString(count));
            for(int i=0;i<count;i++)    //////////////////////////////////////////////////////////////////
            {
                cur.moveToNext();
                TableRow trMain = new TableRow(this);
                trMain.setId(R.id.summRow+i);
                trMain.setBackgroundColor(Color.parseColor("#ffffff"));
                trMain.callOnClick();
                TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT
                );
                params.setMargins(5,3,5,3);
                trMain.setLayoutParams(params);
                trMain.setWeightSum(1f);

                String type=cur.getString(4); ////////
                final int hotel_id=cur.getInt(3);
           /* if(hotel==0)
            {
                hotel=1;
            } */
                ImageView pic = new ImageView(this);
                if(type!=null)
                {
                    //Log.i("HHHHHHHHHHHHH  ", type.toLowerCase()+hotel);
                    //picture load
                    int id = getResources().getIdentifier(type.toLowerCase()+hotel_id, "drawable", getPackageName());
                    Drawable draw = getResources().getDrawable(id);
                    //ImageView pic = findViewById(R.id.roomPicture);
                    pic.setBackground(draw);
                    // int width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0,getResources().getDisplayMetrics()));
                    // int height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0,getResources().getDisplayMetrics()));
                    final float scale = getResources().getDisplayMetrics().density;
                    int pixels = (int) (150 * scale + 0.5f);

                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(0,
                            pixels);
                    layoutParams.weight = 0.35f;
                    pic.setLayoutParams(layoutParams);
                }
                TableLayout miniTable = new TableLayout(this);
                TableRow.LayoutParams p = new TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.MATCH_PARENT
                );
                p.weight = 0.65f;
                p.setMargins(10,5,5,0);
                miniTable.setLayoutParams(p);

                TableRow tr0 = new TableRow(this);
                TextView th0 = new TextView(this);
                th0.setTypeface(null, Typeface.BOLD);
                th0.setText("Start Date: ");
                th0.setTextSize(12);
                TextView td0 = new TextView(this);
                td0.setText(cur.getString(5));        //get date from db
                td0.setTextSize(12);
                tr0.addView(th0);
                tr0.addView(td0);
                miniTable.addView(tr0);

                //Start Time
                TableRow tr1 = new TableRow(this);
                TextView th1 = new TextView(this);
                th1.setTypeface(null, Typeface.BOLD);
                th1.setText("Start Time: ");
                th1.setTextSize(12);
                TextView td1 = new TextView(this);
                td1.setText(cur.getString(7));        //get time from db
                td1.setTextSize(12);
                tr1.addView(th1);
                tr1.addView(td1);
                miniTable.addView(tr1);

                //Hotel Name
                Cursor git=hdb.getHotel(Integer.toString(hotel_id));
                git.moveToNext();
                TableRow tr2 = new TableRow(this);
                TextView th2 = new TextView(this);
                th2.setTypeface(null, Typeface.BOLD);
                th2.setText("Hotel Name: ");
                th2.setTextSize(12);
                TextView td2 = new TextView(this);
                td2.setText(git.getString(git.getColumnIndex("name")));        //get hotel name from db
                td2.setTextSize(12);
                tr2.addView(th2);
                tr2.addView(td2);
                miniTable.addView(tr2);
                git.close();

                //No. of rooms
                TableRow tr3 = new TableRow(this);
                TextView th3 = new TextView(this);
                th3.setTypeface(null, Typeface.BOLD);
                th3.setText("Number of Rooms: ");
                th3.setTextSize(12);
                TextView td3 = new TextView(this);
                td3.setText(cur.getInt(2)+" room(s)");        //get room type from db
                td3.setTextSize(12);
                tr3.addView(th3);
                tr3.addView(td3);
                miniTable.addView(tr3);

                //Arrival Date
                TableRow tr4 = new TableRow(this);
                TextView th4 = new TextView(this);
                th4.setTypeface(null, Typeface.BOLD);
                th4.setText("Arrival Date: ");
                th4.setTextSize(12);
                TextView td4 = new TextView(this);
                td4.setText(cur.getString(5));
                td4.setTextSize(12);
                tr4.addView(th4);
                tr4.addView(td4);
                miniTable.addView(tr4);

                table.addView(trMain);
                trMain.addView(pic);
                trMain.addView(miniTable);

                final int n = i;
                final String user=info;
                final String reserveID=cur.getString(0);
                trMain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Log.i("reserve=========", reserveID);
                        Intent intent = new Intent(ReservationSummary.this, FetchContent.class);
                        intent.putExtra("from", "summary");
                        intent.putExtra("user_id", user);
                        intent.putExtra("hotelID", hotel_id);
                        intent.putExtra("reserveID", reserveID);

                        startActivity(intent);
                    }
                });
            }
        }
        else
        {
            //Toast.makeText(ReservationSummary.this, "INFO is null", Toast.LENGTH_SHORT).show();
        }
        TableRow tableRow = new TableRow(this);
        tableRow.setBackgroundColor(Color.WHITE);
        TableLayout.LayoutParams tp = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT
        );
        tp.setMargins(0, 30, 0, 0);
        tableRow.setLayoutParams(tp);
        Button pend = new Button(this);
        pend.setId(R.id.viewPending);
        pend.setText("View Pending reservations");
        pend.setBackgroundColor(Color.parseColor("#2C1EC6"));
        pend.setPaddingRelative(20, 12, 20, 12);
        pend.setTextColor(Color.WHITE);
        TableRow.LayoutParams p = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT
        );
        p.gravity= Gravity.CENTER_HORIZONTAL;
        pend.setLayoutParams(p);
        tableRow.addView(pend);
        table.addView(tableRow);

        ll.addView(table);
        sv.addView(ll);
        ((LinearLayout) linearLayout).addView(sv);
        hdb.close();
        Button btn = findViewById(R.id.viewPending);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ReservationSummary.this, PendingReservations.class);
                it.putExtra("user_id", info);
                startActivity(it);
            }
        });
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
