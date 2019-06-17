package com.example.day2project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtFirstNumber, edtSecondNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);

        initialize();
    }

    private void initialize() {
        edtFirstNumber = findViewById(R.id.edtFirstNumber);
        edtSecondNumber = findViewById(R.id.edtSecondNumber);
    }

    public void doSum(View view) {
        int num1 = Integer.valueOf(edtFirstNumber.getText().toString());
        int num2 = Integer.valueOf(edtSecondNumber.getText().toString());

        Toast.makeText(this, String.valueOf(num1 + num2), Toast.LENGTH_LONG).show();
    }
}
