package com.example.joe.broanalytics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Categories extends AppCompatActivity {
    private Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        buttonListener();
        listListener();
    }

    private void buttonListener() {
        Button ac = (Button) findViewById(R.id.button_Categories);

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Categories.this, AddCategory.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void listListener() {
        final String[] values = db.getCategories();
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        ListView list = findViewById(R.id.list_Categories);
        list.setAdapter(aa);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent i = new Intent(Categories.this, Activities.class);
                i.putExtra("cat", values[pos]);
                startActivity(i);
                finish();
            }
        });
    }
}
