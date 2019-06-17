package ca.jeonghoon.day4project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Temperature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperture);

        initialize();
    }

    private void initialize() {
        findViewById(R.id.btnConvert).setOnClickListener(view -> displayConvertInfo());
        findViewById(R.id.btnReturn).setOnClickListener(view -> finish());
    }

    private void displayConvertInfo() {
        double celsius = Double.valueOf(((EditText) findViewById(R.id.editText)).getText().toString());
        double fahrenheit = convertCtoF(celsius);

        ((TextView) findViewById(R.id.textTemperatureF)).setText(String.valueOf(fahrenheit));
    }

    private double convertCtoF(double celsius) {
        return celsius * 9/5 + 32;
    }
}
