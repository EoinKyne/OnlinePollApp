package eoinkyne.com.onlinepollapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by computer1 on 20/03/2016.
 */
public class VoterStats extends AppCompatActivity {

    // Declare Widgets
    Spinner spinner1, spinner2;
    Button submit;
    Button taoiseachstats;

    // Declare Strings for Database
    String db_issue;
    String db_age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voterstats);

        // Initialize widgets
        spinner1 = (Spinner) findViewById(R.id.issue);
        spinner2 = (Spinner) findViewById(R.id.age);
        submit = (Button) findViewById(R.id.submitstats);
        taoiseachstats = (Button) findViewById(R.id.taoiseachstats);

        // List of Strings for a Spinner to select which age bracket to filter the data from database for the user
        List<String> list2 = new ArrayList<String>();
        list2.add("Between 18 and 27");
        list2.add("Between 28 and 37");
        list2.add("Between 38 and 50");
        list2.add("Between 51 and 65");
        list2.add("66 +");
        list2.add("All");

        ArrayAdapter<String> dataadapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list2);

        dataadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(dataadapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                Toast.makeText(parent.getContext(), "On Item " +
                                "Selected" + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();

                db_age=parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> paren) {

            }

        });

        // List of Strings for a Spinner to filter the data from the database for the search query
        List<String> list = new ArrayList<String>();
        list.add("Health");
        list.add("Housing");
        list.add("Jobs");
        list.add("Water");
        list.add("Abortion");

        ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataadapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                Toast.makeText(parent.getContext(), "On Item " +
                                "Selected" + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();

                db_issue = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> paren) {

            }

        });

        // Button to collect information selected from widgets and pass to VoterStatsResults
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoterStats.this, VoterStatsResult.class);
                intent.putExtra("age", db_age);
                intent.putExtra("issue", db_issue);
                finish();
                startActivity(intent);
            }
        });

        // Button to see total vote count for each taoiseach candidate
        taoiseachstats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoterStats.this, TaoiseachStats.class);
                finish();
                startActivity(intent);
            }
        });
    }

}
