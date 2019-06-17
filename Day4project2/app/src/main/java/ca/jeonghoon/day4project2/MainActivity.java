package ca.jeonghoon.day4project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MyInfo myInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        myInfo = new MyInfo();

        findViewById(R.id.btnTempConversion).setOnClickListener(this);
        findViewById(R.id.btnMetricConversion).setOnClickListener(this);
        findViewById(R.id.btnFinish).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTempConversion:
                Toast.makeText(this, "Temperature", Toast.LENGTH_LONG).show();
                convertTemperture();
                break;
            case R.id.btnMetricConversion:
                Toast.makeText(this, "Metric", Toast.LENGTH_LONG).show();
                convertMetric();
                break;
            case R.id.btnFinish:
                Toast.makeText(this, "Finish", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }

    private void convertTemperture(){
        Intent myIntent = new Intent(this, Temperature.class);
        startActivity(myIntent);
    }

    private void convertMetric() {
        String lastName = ((EditText) findViewById(R.id.editName)).getText().toString();

        // without bundle
//        Intent myIntent = new Intent(this, Metric.class);
//        myIntent.putExtra("LastName", lastName);
//        startActivity(myIntent);

        // using bundle
        myInfo.lastName = lastName;

        Intent myIntent = new Intent(this, Metric.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myInfo", myInfo);
        myIntent.putExtras(bundle);
        startActivity(myIntent);
    }
}
