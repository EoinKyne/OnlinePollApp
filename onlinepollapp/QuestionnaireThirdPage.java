package eoinkyne.com.onlinepollapp;

import android.content.Context;
import android.content.Intent;
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
public class QuestionnaireThirdPage extends AppCompatActivity {

    // Declare Widgets, Database Strings and strings for Taoiseach candidate and parties
    ListView list;
    Button next;
    String db_Taoiseach;

    String db_age;
    String db_gender;
    String db_voterstatus;
    String db_locality;
    String db_issue;
    String db_income;
    String db_politicalparty;

    String[] Taoiseach = {"Enda Kenny", "Michael Martin", "Gerry Adams"};
    String[] Party = {"FG", "FF", "SF"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnairethirdpage);

        //
        list = (ListView) findViewById(R.id.listcand);
        next = (Button) findViewById(R.id.confirmnext);

        // Collect information from first and second page of questionnaire
        Bundle bundle_db = getIntent().getExtras();
        db_age= bundle_db.getString("age");
        db_gender = bundle_db.getString("gender");
        db_voterstatus = bundle_db.getString("voterstatus");
        db_locality = bundle_db.getString("locality");
        db_issue = bundle_db.getString("issue");
        db_income = bundle_db.getString("income");
        db_politicalparty = bundle_db.getString("political_party");

        //custom list view to select perferred Taoiseach
        list.setAdapter(new MyCustomAdapter(QuestionnaireThirdPage.this, R.layout.rowthirdpage, Taoiseach, Party));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db_Taoiseach = (String) list.getItemAtPosition(position);
                Toast.makeText(getApplication(), db_Taoiseach, Toast.LENGTH_LONG).show();
            }
        });

        // Button to collect information and send it to the fourth questionnaire class.
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QuestionnaireThirdPage.this, QuestionnaireFourthPage.class);
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

    // This is a custom adapter to hold
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

            if (Taoiseach[position] == "Enda Kenny"){
                icon.setImageResource(R.drawable.ek);
                icon2.setText(Party[position]);
            }
            else if (Taoiseach[position] == "Michael Martin"){
                icon.setImageResource(R.drawable.mm);
                icon2.setText(Party[position]);
            }
            else if (Taoiseach[position] == "Gerry Adams"){
                icon.setImageResource(R.drawable.ga);
                icon2.setText(Party[position]);
            }
            return row;
        }

    }

}
