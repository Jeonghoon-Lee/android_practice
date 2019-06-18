package ca.jeonghoon.day5passingindent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText textClientId;
    EditText textClientEmail;
    RadioGroup radioGrMovieType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        textClientId = (EditText) findViewById(R.id.editClientNumber);
        textClientEmail = (EditText) findViewById(R.id.editClientEmail);
        radioGrMovieType = (RadioGroup) findViewById(R.id.radioGrMovieType);

        // Add event listener for buttons
        findViewById(R.id.btnClear).setOnClickListener(this);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnRemove).setOnClickListener(this);
        findViewById(R.id.btnUpdateEmail).setOnClickListener(this);
        findViewById(R.id.btnShowAll).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClear:
                break;

        }
    }
}
