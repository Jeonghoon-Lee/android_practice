package ca.jeonghoon.day4project3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Test2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        initialize();
    }

    private void initialize() {
        findViewById(R.id.btnOk).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent();
        switch (v.getId()) {
            case R.id.btnOk:
                myIntent.putExtra("return_result_from_test2", "OK result");
                setResult(RESULT_OK, myIntent);
                break;
            case R.id.btnCancel:
                myIntent.putExtra("return_result_from_test2", "Cancel result");
                setResult(RESULT_CANCELED, myIntent);
                break;
        }
        finish();
    }
}
