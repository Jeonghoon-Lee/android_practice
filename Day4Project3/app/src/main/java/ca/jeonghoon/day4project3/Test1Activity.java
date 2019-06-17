package ca.jeonghoon.day4project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Test1Activity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        initialize();
    }

    private void initialize() {
        textViewQuestion = findViewById(R.id.textQuestion);

        findViewById(R.id.btnGenerate).setOnClickListener(this);
        findViewById(R.id.btnValidate).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGenerate:
                break;
            case R.id.btnValidate:
                break;
            case R.id.btnCancel:
                setResult(RESULT_CANCELED, null);
                finish();
                break;
        }
    }
}
