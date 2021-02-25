package com.example.projects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText type_input, name_input, description_input;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        type_input=findViewById(R.id.type_input);
        name_input=findViewById(R.id.name_input);
        description_input=findViewById(R.id.description_input);
        add_button=findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addProject(type_input.getText().toString().trim(), name_input.getText().toString().trim(), description_input.getText().toString().trim());
            }
        });
    }
}