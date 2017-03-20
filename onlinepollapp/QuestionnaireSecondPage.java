package eoinkyne.com.onlinepollapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by computer1 on 07/03/2016.
 */
public class QuestionnaireSecondPage extends AppCompatActivity {

    //declare widgets
    Spinner spinner2;
    Spinner spinner3;
    RadioGroup politicalparty;
    Button add;

    //declaring strings for database
    String db_age;
    String db_gender;
    String db_voterstatus;
    String db_locality;
    String db_issue;
    String db_income;
    String db_politicalparty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnairesecondpage);

        //Initialize widgets
        spinner2 = (Spinner) findViewById(R.id.issue);
        spinner3 = (Spinner) findViewById(R.id.income);
        politicalparty = (RadioGroup) findViewById(R.id.polparty);
        add = (Button) findViewById(R.id.confirmnext);

        //Collect information sent from first page of Questionnaire
        Bundle bundle_db = getIntent().getExtras();
        db_age= bundle_db.getString("age");
        db_gender = bundle_db.getString("gender");
        db_voterstatus = bundle_db.getString("voterstatus");
        db_locality = bundle_db.getString("locality");


        // spinner used to determine issues that relate to the correspondant
        List<String> list2 = new ArrayList<String>();
        list2.add("Health");
        list2.add("Housing");
        list2.add("Jobs");
        list2.add("Water");
        list2.add("Abortion");

        ArrayAdapter<String> dataadapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list2);

        dataadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(dataadapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                Toast.makeText(parent.getContext(), "On Item " +
                                "Selected" + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();

                db_issue = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> paren) {

            }
        });

        // Spinner to determine income bracket of correspendant
        List<String> list3 = new ArrayList<String>();
        list3.add("No Income");
        list3.add("20,000 - 30,000");
        list3.add("30,000 - 40,000");
        list3.add("40,000 - 50,000");
        list3.add("50,000 - 75,000");
        list3.add("75,000 + ");

        ArrayAdapter<String> dataadapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list3);

        dataadapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner3.setAdapter(dataadapter3);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                Toast.makeText(parent.getContext(), "On Item " +
                                "Selected" + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();

                db_income = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> paren) {

            }
        });

        // Radio button to ask if the correspondant is a member of a political party
        politicalparty.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch (id) {
                    case R.id.radiomem:
                        db_politicalparty = "A Member of a Political Party";
                        break;
                    case R.id.radionotmem:
                        db_politicalparty = "Not a Member of a Political Party";
                        break;
                    default:
                        break;
                }
                db_politicalparty = ((RadioButton) findViewById(politicalparty.getCheckedRadioButtonId())).getText().toString();

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionnaireSecondPage.this, QuestionnaireThirdPage.class);
                intent.putExtra("issue", db_issue);
                intent.putExtra("income", db_income);
                intent.putExtra("political_party", db_politicalparty);
                intent.putExtra("gender", db_gender);
                intent.putExtra("age", db_age);
                intent.putExtra("voterstatus", db_voterstatus);
                intent.putExtra("locality", db_locality);
                finish();
                startActivity(intent);
            }
        });

    }

}
