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

public class AddActivity extends AppCompatActivity {
    private Database db = new Database(this);
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        category = getIntent().getStringExtra("cat");
        TextView t = findViewById(R.id.textView_AddActivity);
        t.setText(category);
        buttonListener();
    }

    private void buttonListener() {
        Button add = (Button) findViewById(R.id.button_AddActivity);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et3 = (EditText) findViewById(R.id.editText_AddActivityF1);
                EditText et2 = (EditText) findViewById(R.id.editText_AddActivityF2);
                EditText et1 = (EditText) findViewById(R.id.editText_AddActivityName);
                if(db.addActivity(et1.getText().toString(), et2.getText().toString(),
                        et3.getText().toString(), category)) Toast.makeText(getApplicationContext(),
                        "worked", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                Intent i = new Intent(AddActivity.this, Activities.class);
                i.putExtra("cat", category);
                startActivity(i);
                finish();
            }
        });

    }
}
