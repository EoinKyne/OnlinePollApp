package eoinkyne.com.onlinepollapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by computer1 on 26/02/2016.
 */
public class Questionnaire extends AppCompatActivity  {

    // Declare Widgets, Database Strings
    RadioGroup gender;
    RadioGroup voterstatus;
    Spinner spinner1;
    Button next;

    EditText db_age;
    String db_gender;
    String db_voterstatus;
    String db_locality;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);

        // Initialize
        db_age = (EditText) findViewById(R.id.age);
        gender = (RadioGroup) findViewById(R.id.gender);
        voterstatus = (RadioGroup) findViewById(R.id.voterstatus);
        spinner1 = (Spinner) findViewById(R.id.locality);
        next = (Button) findViewById(R.id.confirmnext);

        // Radio button to collect the gender of correspondants
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch (id) {
                    case R.id.radiom:
                        db_gender = "Male";
                        break;
                    case R.id.radiof:
                        db_gender = "Female";
                        break;
                    default:
                        break;
                }
                db_gender = ((RadioButton) findViewById(gender.getCheckedRadioButtonId())).getText().toString();

            }
        });

        //Radio button to check if the correspondent is a first time voter
        voterstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch (id) {
                    case R.id.voterft:
                        db_voterstatus = "First Time Voter";
                        break;
                    case R.id.voternft:
                        db_voterstatus = "Not a First Time Voter";
                        break;
                    default:
                        break;
                }
                db_voterstatus = ((RadioButton) findViewById(voterstatus.getCheckedRadioButtonId())).getText().toString();

            }
        });

        // breakdown the correspondants into geographical areas in the electoral area
        List<String> list = new ArrayList<String>();
        list.add("Galway West");
        list.add("Galway City West");
        list.add("Galway City East");
        list.add("South Mayo");

        ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataadapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                Toast.makeText(parent.getContext(), "On Item " +
                                "Selected" + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();

                db_locality = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> paren) {

            }

        });

        // button to bundle the collected information to the second page
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Questionnaire.this, QuestionnaireSecondPage.class);
                intent.putExtra("age", db_age.getText().toString());
                intent.putExtra("gender", db_gender);
                intent.putExtra("voterstatus", db_voterstatus);
                intent.putExtra("locality", db_locality);
                finish();
                startActivity(intent);
            }
        });
    }
}
