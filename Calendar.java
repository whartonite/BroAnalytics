package com.example.joe.broanalytics;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar extends AppCompatActivity {
    String activity;
    String category;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        activity = getIntent().getStringExtra("act");
        category = getIntent().getStringExtra("cat");
        setOnCalendarChangeListener();
    }

    private void setOnCalendarChangeListener() {
        final CalendarView cV = findViewById(R.id.calendarView);
        cV.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = dateHelper(i2, 2) + dateHelper(i1 + 1, 2) + i;
            }
        });
        buttonListener();
    }

    private String dateHelper(int num, int digits) {
        StringBuffer s = new StringBuffer(digits);
        int zeroes = digits - (int) (Math.log(num) / Math.log(10)) - 1;
        for (int i = 0; i < zeroes; i++) {
            s.append(0);
        }
        return s.append(num).toString();
    }

    private void buttonListener() {
        Button b = findViewById(R.id.button_ReturnDate);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in;
                if (getIntent().getStringExtra("nextActivity").equals("data")) {
                    in = new Intent(Calendar.this, AddData.class);
                }
                else {
                    in = new Intent(Calendar.this, EditData.class);
                }
                in.putExtra("date", date);
                in.putExtra("cat", category);
                in.putExtra("act", activity);
                startActivity(in);
                finish();
            }
        });
    }
}
