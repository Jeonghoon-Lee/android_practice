package ca.jeonghoon.day4project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Metric extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metric);

        initialize();
        getIntentInfo();
    }

    private void initialize() {
        findViewById(R.id.btnConvert).setOnClickListener(view -> convertAndDisplay());
        // Return to main
        findViewById(R.id.btnReturn).setOnClickListener(view -> finish());
    }

    private void getIntentInfo() {
        // without bundle
//        ((TextView) findViewById(R.id.textName)).setText(getIntent().getStringExtra("LastName"));

        // with bundle
        Bundle bundle = getIntent().getExtras();
        MyInfo myInfo = (MyInfo) bundle.getSerializable("myInfo");
        ((TextView) findViewById(R.id.textName)).setText(myInfo.lastName);
    }

    private void convertAndDisplay() {
        String strValue = ((EditText) findViewById(R.id.editText)).getText().toString();

        double centimeter = Double.valueOf(strValue) * 1000;
        ((TextView) findViewById(R.id.textCentimeter)).setText(String.valueOf(centimeter));

        double kilometer = Double.valueOf(strValue) / 1000;
        ((TextView) findViewById(R.id.textKilometer)).setText(String.valueOf(kilometer));
    }
}
