package eoinkyne.com.onlinepollapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by computer1 on 03/03/2016.
 */
public class FinalPage extends AppCompatActivity{

    // Declare Widgets
    TextView text1;
    Button btn1;

    // declare strings for information collected in questionnaire
    String db_age;
    String db_gender;
    String db_voterstatus;
    String db_locality;
    String db_issue;
    String db_income;
    String db_politicalparty;
    String db_Taoiseach;
    String db_firstpreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalpage);

        // Initialize Widgets
        text1 = (TextView) findViewById(R.id.back);
        btn1 = (Button) findViewById(R.id.button);

        //collect all the information from first, second, third and fourth pages of questionnaires and print to screen
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            text1.setText(bundle.getString("age") + "\n");
            text1.append(bundle.getString("gender") + "\n");
            text1.append(bundle.getString("voterstatus") + "\n");
            text1.append(bundle.getString("locality") + "\n");
            text1.append(bundle.getString("issue") + "\n");
            text1.append(bundle.getString("income") + "\n");
            text1.append(bundle.getString("political_party") + "\n");
            text1.append(bundle.getString("Taoiseach") + "\n");
            text1.append(bundle.getString("FirstPreference") + "\n");
        }
        //collect all the information from first, second, third and fourth pages of questionnaires for database
        Bundle bundle_db = getIntent().getExtras();
        db_age= bundle_db.getString("age");
        db_gender = bundle_db.getString("gender");
        db_voterstatus = bundle_db.getString("voterstatus");
        db_locality = bundle_db.getString("locality");
        db_issue = bundle_db.getString("issue");
        db_income = bundle_db.getString("income");
        db_politicalparty = bundle_db.getString("political_party");
        db_Taoiseach = bundle_db.getString("Taoiseach");
        db_firstpreference = bundle_db.getString("FirstPreference");


        // Button to go back to the main menu
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

}
