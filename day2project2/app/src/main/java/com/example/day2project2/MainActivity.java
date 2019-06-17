package com.example.day2project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
    }

    public void display(View view) {
        Toast.makeText(this,
                String.format("Student id: %s\nName: %s\nAge:%s",
                        editText1.getText().toString(),
                        editText2.getText().toString(),
                        editText3.getText().toString()),
                Toast.LENGTH_LONG).show();
    }
}
