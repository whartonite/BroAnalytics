package com.example.joe.broanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCategory extends AppCompatActivity {
    private Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        buttonListener();
    }

    private void buttonListener() {
        Button add = (Button) findViewById(R.id.button_AddCategory);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et1 = (EditText) findViewById(R.id.editText_addCategory);
                if(db.addCategory(et1.getText().toString())) Toast.makeText(getApplicationContext(),
                        "worked", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                Intent i = new Intent(AddCategory.this, Categories.class);
                startActivity(i);
                finish();
            }
        });

    }
}
