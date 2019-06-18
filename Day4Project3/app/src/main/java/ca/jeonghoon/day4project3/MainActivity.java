package ca.jeonghoon.day4project3;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int REQUEST_CODE1 = 1;
    private final int REQUEST_CODE2 = 2;

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        textViewResult = findViewById(R.id.textResult);

        findViewById(R.id.btnTest1).setOnClickListener(this);
        findViewById(R.id.btnTest2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnTest1) {
            openTest1Activity();
        } else {
            openTest2Activity();
        }
    }

    private void openTest1Activity() {
        Intent myIntent = new Intent(this, Test1Activity.class);
        startActivityForResult(myIntent, REQUEST_CODE1);
    }

    private void openTest2Activity() {
        Intent myIntent = new Intent(this, Test2Activity.class);
        startActivityForResult(myIntent, REQUEST_CODE2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE1) {
            String result;
            if (resultCode == RESULT_OK) {
                result = data.getStringExtra("return_result_tag");
            } else {
                result = data != null ? data.getStringExtra("cancel_tag") : "cancel";
            }
            textViewResult.setText(result);
        } else {
            if (data != null) {
                String result = data.getStringExtra("return_result_from_test2");
                textViewResult.setText(result);
            }
        }
    }
}
