package eoinkyne.com.onlinepollapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by computer1 on 15/03/2016.
 */
public class QuestionnaireFourthPage extends AppCompatActivity {

    // Declare Widgets, Database Strings, database manager and strings for First preference candidate and parties
    ListView list;
    Button next;

    String db_age;
    String db_gender;
    String db_voterstatus;
    String db_locality;
    String db_issue;
    String db_income;
    String db_politicalparty;
    String db_Taoiseach;
    String db_firstpreference;

    private DbManager db;

    String[] First_Preference = {"Derek Nolan", "Fidelma Healy Eames", "Hildergarde Naughton", "John Connolly", "Mary Hoarde", "Noel Grealish", "Sean Kyne"};
    String[] Party = {"LAB", "IND", "FG", "FF", "FF", "IND", "FG"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnairefourthpage);


        list = (ListView) findViewById(R.id.listcand);
        next = (Button) findViewById(R.id.confirmnext);


        db = new DbManager(this);

        // Collect all the information from first, second and third page of questionnaire
        Bundle bundle_db = getIntent().getExtras();
        db_age= bundle_db.getString("age");
        db_gender = bundle_db.getString("gender");
        db_voterstatus = bundle_db.getString("voterstatus");
        db_locality = bundle_db.getString("locality");
        db_issue = bundle_db.getString("issue");
        db_income = bundle_db.getString("income");
        db_politicalparty = bundle_db.getString("political_party");
        db_Taoiseach = bundle_db.getString("Taoiseach");

        // custom listview to determine perferred first preference
        list.setAdapter(new MyCustomAdapter(QuestionnaireFourthPage.this, R.layout.rowthirdpage, First_Preference, Party));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db_firstpreference = (String) list.getItemAtPosition(position);
                Toast.makeText(getApplication(), db_firstpreference, Toast.LENGTH_LONG).show();
            }
        });

        // Button to open database and add a single row of data to the database and collect data and send it to finalpage
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.open();

                addSingleRow(db_age, db_gender, db_voterstatus, db_locality, db_issue, db_income, db_politicalparty, db_Taoiseach, db_firstpreference);

                db.close();

                Intent intent = new Intent(QuestionnaireFourthPage.this, FinalPage.class);
                intent.putExtra("FirstPreference", db_firstpreference);
                intent.putExtra("Taoiseach", db_Taoiseach);
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


    public class MyCustomAdapter extends ArrayAdapter<String> {

        String[] names;
        String[] parties;

        public MyCustomAdapter(Context context, int textViewResourcesId,
                               String[] objects, String[] results){
            super(context, textViewResourcesId, objects);
            names=objects;
            parties=results;

        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater=getLayoutInflater();
            View row = inflater.inflate(R.layout.rowthirdpage, parent, false);
            TextView label = (TextView)row.findViewById(R.id.candidate);
            label.setText(names[position]);
            ImageView icon = (ImageView)row.findViewById(R.id.icon);
            TextView icon2 = (TextView)row.findViewById(R.id.icon2);

            if (First_Preference[position] == "Derek Nolan"){
                icon.setImageResource(R.drawable.dn);
                icon2.setText(Party[position]);
            }
            else if (First_Preference[position] == "Fidelma Healy Eames"){
                icon.setImageResource(R.drawable.fhe);
                icon2.setText(Party[position]);
            }
            else if (First_Preference[position] == "Hildergarde Naughton"){
                icon.setImageResource(R.drawable.hn);
                icon2.setText(Party[position]);
            }
            else if (First_Preference[position] == "John Connolly"){
                icon.setImageResource(R.drawable.jc);
                icon2.setText(Party[position]);
            }
            else if (First_Preference[position] == "Mary Hoarde"){
                icon.setImageResource(R.drawable.mh);
                icon2.setText(Party[position]);
            }
            else if (First_Preference[position] == "Noel Grealish"){
                icon.setImageResource(R.drawable.ng);
                icon2.setText(Party[position]);
            }
            else if (First_Preference[position] == "Sean Kyne"){
                icon.setImageResource(R.drawable.sk);
                icon2.setText(Party[position]);
            }
            return row;
        }

    }


    public void addSingleRow(String age, String gender, String voterstatus, String locality, String issue, String income, String politicalparty,
                             String taoiseach, String firstpreference)
    {
        long id;
        id = db.insertPoll(age, gender, voterstatus, locality, issue, income, politicalparty, taoiseach, firstpreference);
    }
    public void addRows()
    {
        long id;
        id = db.insertPoll(
                "Open Age", "Open Gender", "Open Voterstatus", "Open Locality", "Open Issue", "Open Income", "Open PoliticalParty", "Open Taoiseach", "Open FirstPreference");
        id = db.insertPoll(
                "Close Age", "Close Gender", "Close Voterstatus", "Close Locality", "Close Issue", "Close Income", "Close PoliticalParty", "Close Taoiseach", "Close FirstPreference");
    }

    //---get all people---
    public void getRows() {
        Cursor c = db.getAllPolls();
        if (c.moveToFirst()) {
            do {
                ShowPoll(c);

            }
            while (c.moveToNext());
        }
    }

    public void ShowPoll(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Age: " + c.getString(1) + "\n" +
                        "Gender: " + c.getString(2) + "\n" +
                        "Voterstatus: " + c.getString(3) + "\n" +
                        "Locality: " + c.getString(4) + "\n" +
                        "Issue: " + c.getString(5) + "\n" +
                        "Income: " + c.getString(6) + "\n" +
                        "PoliticalParty: " + c.getString(7) + "\n" +
                        "Taoiseach: " + c.getString(8) + "\n" +
                        "FirstPreference: " + c.getString(9) + "\n",
                Toast.LENGTH_LONG).show();
    }

}
