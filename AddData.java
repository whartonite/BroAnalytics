package com.example.joe.broanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddData extends AppCompatActivity {
    private Database db = new Database(this);
    String category;
    String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        category = getIntent().getStringExtra("cat");
        activity = getIntent().getStringExtra("act");
        buttonListener();
        buttonListener2();
        setTextView();
    }

    private void setTextView() {
        TextView t = findViewById(R.id.textView_AD_Activity);
        t.setText(activity);
    }

    private void buttonListener2() {
        Button b = findViewById(R.id.button_CustomDate);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddData.this, com.example.joe.broanalytics.Calendar.class);
                i.putExtra("nextActivity", "data");
                i.putExtra("act", activity);
                i.putExtra("cat", category);
                startActivity(i);
                finish();
            }
        });
    }

    private void buttonListener() {
        Button b = (Button) findViewById(R.id.button_AddRecord);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et1 = (EditText) findViewById(R.id.editText_field1);
                EditText et2 = (EditText) findViewById(R.id.editText_field2);
                String[] ss = new String[2];
                ss[0] = et1.getText().toString();
                ss[1] = et2.getText().toString();
                String formattedDate = getIntent().getStringExtra("date");
                if (formattedDate == null) {
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
                    formattedDate = df.format(c);
                }
                if(db.addData(ss, formattedDate, activity)) Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                Intent i = new Intent(AddData.this, Activities.class);
                i.putExtra("cat", category);
                startActivity(i);
                finish();
            }
        });
    }
}
