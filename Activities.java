package com.example.joe.broanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activities extends AppCompatActivity {
    private Database db = new Database(this);
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        category = getIntent().getStringExtra("cat");
        buttonListener();
        listListener();
        setTextView();
    }

    private void setTextView() {
        TextView t = findViewById(R.id.textView_Category);
        t.setText(category);
    }

    private void buttonListener() {
        Button aa = (Button) findViewById(R.id.button_Activity);

        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activities.this, AddActivity.class);
                i.putExtra("cat", category);
                startActivity(i);
                finish();
            }
        });
    }

    private void listListener() {
        final String[] values = db.getActivities(category);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);

        ListView list = findViewById(R.id.list_Activities);
        list.setAdapter(aa);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent i = new Intent(Activities.this, AddData.class);
                i.putExtra("act", values[pos]);
                i.putExtra("cat", category);
                startActivity(i);
                finish();
            }
        });
    }
}
