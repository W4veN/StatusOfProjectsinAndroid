package com.example.projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText type_input, name_input, description_input;
    Button update_button;

    String id, type, name, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        type_input = findViewById(R.id.type_input2);
        name_input = findViewById(R.id.name_input2);
        description_input = findViewById(R.id.description_input2);
        update_button = findViewById(R.id.update_button);
        //First this
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                //Then this
                type=type_input.getText().toString().trim();
                name=name_input.getText().toString().trim();
                description=description_input.getText().toString().trim();
                myDB.updateData(id, type, name, description);
            }
        });


    }

    void getAndSetIntentData () {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("type") && getIntent().hasExtra("name") && getIntent().hasExtra("description")) {
            //Getting data from Intent
            id = getIntent().getStringExtra("id");
            type = getIntent().getStringExtra("type");
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");

            //Setting Intent data
            type_input.setText(type);
            name_input.setText(name);
            description_input.setText(description);

        }
        else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

}