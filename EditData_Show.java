package com.example.joe.broanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EditData_Show extends AppCompatActivity {
    private String activity;
    private String date;
    private String id;
    private Database db = new Database(this);
    EditText et1;
    EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data__show);

        activity = getIntent().getStringExtra("act");
        date = getIntent().getStringExtra("date");
        et1 = findViewById(R.id.editText_EditDataField1);
        et2 = findViewById(R.id.editText_EditDataField2);
        TextView tv1 = findViewById(R.id.textView_EditDataField1);
        TextView tv2 = findViewById(R.id.textView_EditDataField2);
        TextView tv3 = findViewById(R.id.textView_EditDataActivity);
        String[] ss = db.getFields(activity, date);
        tv1.setText(ss[0]);
        tv2.setText(ss[1]);
        tv3.setText(activity);

        buttonListener1();
        buttonListener2();
    }

    private void buttonListener1() {
        Button b = findViewById(R.id.button_cancel);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditData_Show.this, EditData.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void buttonListener2() {
        Button b = findViewById(R.id.button_update);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] ss = new String[2];
                ss[0] = et1.getText().toString();
                ss[1] = et2.getText().toString();
                if (db.updateData(ss, activity, date, Integer.parseInt(id))) Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                Intent i = new Intent(EditData_Show.this, EditData.class);
                startActivity(i);
                finish();
            }
        });
    }
}
