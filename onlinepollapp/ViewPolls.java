package eoinkyne.com.onlinepollapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by computer1 on 19/03/2016.
 */
public class ViewPolls extends AppCompatActivity {

    private DbManager db;
    LinearLayout container;
    //Button btn1;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpolls);

        container = (LinearLayout)findViewById(R.id.database);

        db = new DbManager(this);

        db.open();

        getRows();

        db.close();

    }


    //---get all people---
    public void getRows() {
        Cursor c = db.getAllPolls();
        if (c.moveToFirst()) {
            do {
                ShowPolls(c);

            }
            while (c.moveToNext());
        }
    }

    public void ShowPolls(Cursor c)
    {

        TextView text = new TextView(this);

        text.setText("id: " + c.getString(0) + "\n" +
                "Age: " + c.getString(1) + "\n" +
                "Gender: " + c.getString(2) + "\n" +
                "Voterstatus: " + c.getString(3) + "\n" +
                "Locality: " + c.getString(4) + "\n" +
                "Issue: " + c.getString(5) + "\n" +
                "Income: " + c.getString(6) + "\n" +
                "PoliticalParty: " + c.getString(7) + "\n" +
                "Taoiseach: " + c.getString(8) + "\n" +
                "FirstPreference: " + c.getString(9));

        container.addView(text);

        //btn1 = (Button) findViewById(R.id.button);

        /*btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });*/
    }


}
