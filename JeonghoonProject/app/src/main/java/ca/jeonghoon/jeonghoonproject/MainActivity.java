package ca.jeonghoon.jeonghoonproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.buttonDot).setOnClickListener(this);
        findViewById(R.id.buttonMinus).setOnClickListener(this);

        findViewById(R.id.buttonGenerate).setOnClickListener(this);
        findViewById(R.id.buttonValidate).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);

        findViewById(R.id.buttonScore).setOnClickListener(this);
        findViewById(R.id.buttonFinish).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
            case R.id.button0:
            case R.id.buttonDot:
            case R.id.buttonMinus:
                break;

            case R.id.buttonGenerate:
                break;
            case R.id.buttonValidate:
                break;
            case R.id.buttonClear:
                break;

            case R.id.buttonScore:
                score();
                break;
            case R.id.buttonFinish:
                finish();
                break;
        }
    }

    private void score() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}
