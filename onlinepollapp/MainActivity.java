package eoinkyne.com.onlinepollapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declare Widgets
    private Button add;
    private Button view;
    private Button stats;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize widgets
        add = (Button)findViewById(R.id.add);
        view = (Button)findViewById(R.id.view);
        stats = (Button)findViewById(R.id.pollstats);

        // Button to add new poll to the database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Questionnaire.class);
                startActivity(intent);
            }
        });

        // Button to view all the poll data collected
       view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewPolls.class);
                startActivity(intent);
            }
        });

        // Button to get statitical information on the database
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VoterStats.class);
                startActivity(intent);
            }
        });

    }

}
