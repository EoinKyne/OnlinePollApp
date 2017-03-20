package eoinkyne.com.onlinepollapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by computer1 on 20/03/2016.
 */
public class VoterStatsResult extends AppCompatActivity {

    // Declare Widgets, database and integer variables
    TextView results;
    LinearLayout container;
    Button btn1;

    DbManager db;

    int upperage;
    int lowerage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voterstatsresults);

        //Initialize widgets
        results = (TextView)findViewById(R.id.voterstatsresult);
        container = (LinearLayout)findViewById(R.id.stats);
        btn1 = (Button) findViewById(R.id.button);

        // Initialize database Manager
        db = new DbManager(this);


        // Collect information sent from VoterStats class
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null)
        {
            results.setText(bundle.getString("age"));
            results.append(bundle.getString("issue") + "\n");
        }

        // Group Age Integers in Database between an lower and upper age to match the Strings selected by users
        if(bundle.getString("age").compareTo("Between 18 and 27")==0)
        {
            lowerage=17;
            upperage=28;
        }
        else if(bundle.getString("age").compareTo("Between 28 and 37")==0)
        {
            lowerage=27;
            upperage=38;
        }
        else if(bundle.getString("age").compareTo("Between 38 and 50")==0)
        {
            lowerage=37;
            upperage=51;
        }
        else if(bundle.getString("age").compareTo("Between 51 and 65")==0)
        {
            lowerage=50;
            upperage=66;
        }
        else if(bundle.getString("age").compareTo("66 +")==0)
        {
            lowerage=65;
            upperage=120;
        }
        else if(bundle.getString("age").compareTo("All")==0)
        {
            lowerage=0;
            upperage=120;
        }

        // Do Database Lookup
        db.open();

        getRows(bundle.getString("issue"), lowerage, upperage);

        db.close();

        // Button to go back to main menu
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    // method to go through the rows in the database and cursor collects all information in rows
    public void getRows(String issue, int up_age, int low_age) {
        Cursor c = db.getPollByIssueAndAge(issue, up_age,low_age);

        if (c.moveToFirst()) {
            do {
                ShowTask(c);

            }
            while (c.moveToNext());

        }
    }

    // method to output the information collected by the cursor and print in a textview
    public void ShowTask(Cursor c)
    {
        TextView text = new TextView(this);

        results.append(" id: " + c.getString(0) + "\n" +
                "Age: " + c.getString(1) + "\n" +
                "Gender: " + c.getString(2) + "\n" +
                "First Time Voter: " + c.getString(3) + "\n" +
                "Electoral Area: " + c.getString(4) + "\n" +
                "Issue: " + c.getString(5) + "\n" +
                "Income: " + c.getString(6) + "\n" +
                "Member of Political Party: " + c.getString(7) + "\n" +
                "Preferred Taoiseach: " + c.getString(8) + "\n" +
                "Preferred First Preference: " + c.getString(9) + "\n");

        container.addView(text);
    }
}
