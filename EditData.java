package com.example.joe.broanalytics;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditData extends AppCompatActivity {
    private Database db = new Database(this);
    private String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        activity = getIntent().getStringExtra("act");
        listListenerCat();
        buttonListener();
        buttonListener2();
    }

    private void listListenerCat() {
        final String[] values = db.getCategories();

        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        Log.d("mytag", "listListenerCat:"+R.id.listView_cat);
        ListView list = findViewById(R.id.listView_cat);
        list.setAdapter(aa);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                //update the list act is based on and refresh it
                listListenerAct(values[pos]);
            }
        });
    }

    private void listListenerAct(String category) {
        final String[] values = db.getActivities(category);

        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);

        ListView list = findViewById(R.id.listView_act);
        list.setAdapter(aa);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                activity = values[pos];

            }
        });
    }

    private void buttonListener() {
        Button b = findViewById(R.id.button_ChooseDate);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditData.this, Calendar.class);
                i.putExtra("nextActivity", "editdata");
                i.putExtra("act", activity);
                //i.putExtra("cat", category);
                startActivity(i);
                TextView t = findViewById(R.id.textView_Date);
                t.setText(getIntent().getStringExtra("date"));
            }
        });
    }

    private void buttonListener2() {
        Button b = findViewById(R.id.button_Search);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity == null) return;
                String formattedDate = getIntent().getStringExtra("date");
                if (formattedDate == null) {
                    Date c = java.util.Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
                    formattedDate = df.format(c);
                }
                int id = db.dataExists(activity, formattedDate);
                Toast.makeText(getApplicationContext(), id+"", Toast.LENGTH_LONG).show();
                if (id != 0) {
                    Intent i = new Intent(EditData.this, EditData_Show.class);
                    i.putExtra("act", activity);
                    i.putExtra("date", getIntent().getStringExtra("date"));
                    startActivity(i);
                }
            }
        });
    }
}
