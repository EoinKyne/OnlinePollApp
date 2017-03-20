package eoinkyne.com.onlinepollapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by computer1 on 01/04/2016.
 */
public class TaoiseachStats extends AppCompatActivity {

    //Declare Widgets
    TextView results;
    LinearLayout container;
    Button btn1;

    DbManager db;

    // Declare Strings for each taoiseach candidate
    String EndaKenny = "Enda Kenny";
    String MichaelMartin = "Michael Martin";
    String GerryAdams = "Gerry Adams";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taoiseachstats);

        //Initialize widgets
        results = (TextView)findViewById(R.id.taoiseachresult);
        container = (LinearLayout)findViewById(R.id.stats);
        btn1 = (Button) findViewById(R.id.button);

        // Initialize database Manager
        db = new DbManager(this);

        // Do Database Lookup for each method
        db.open();

        getEndaKenny();
        getMichaelMartin();
        getGerryAdams();

        db.close();

        // Button to return to Main Menu
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    // Method to collect all the votes Enda Kenny
    public void getEndaKenny() {
        Cursor c = db.getTaoiseachTotalCount(EndaKenny);

        int total = c.getCount();
        TextView text = new TextView(this);
        if (c.moveToFirst()) {
            do {
                text.setText(c.getCount() + " votes :- " + c.getString(8) +  "\n");
            }
            while (c.moveToNext());

            container.addView(text);
        }
    }

    // Method to collect all votes Michael Martin
    public void getMichaelMartin() {
        Cursor c = db.getTaoiseachTotalCount(MichaelMartin);

        TextView text = new TextView(this);
        if (c.moveToFirst()) {
            do {
                text.setText(c.getCount() + " votes :- " + c.getString(8) +  "\n");
            }
            while (c.moveToNext());

            container.addView(text);
        }
    }

    // Method to collect all votes for Gerry Adams
    public void getGerryAdams() {
        Cursor c = db.getTaoiseachTotalCount(GerryAdams);

        int total = c.getCount();
        TextView text = new TextView(this);
        if (c.moveToFirst()) {
            do {
                text.setText(c.getCount() + " votes :- " + c.getString(8) + "\n");
            }
            while (c.moveToNext());

            container.addView(text);
        }
    }

}
