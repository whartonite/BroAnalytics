package com.example.joe.broanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        buttonListeners();
    }

    private void buttonListeners() {
        Button nd = (Button)findViewById(R.id.button_AddNewData);
        Button ed = (Button)findViewById(R.id.button_EditData);
        Button vt = (Button)findViewById(R.id.button_ViewTrends);
        Button st = (Button)findViewById(R.id.button_Settings);

        nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, Categories.class);
                startActivity(i);
            }
        });
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, EditData.class);
                startActivity(i);
            }
        });
        vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, GraphMenu.class);
                startActivity(i);
            }
        });
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, Settings.class);
                startActivity(i);
            }
        });
    }
}
