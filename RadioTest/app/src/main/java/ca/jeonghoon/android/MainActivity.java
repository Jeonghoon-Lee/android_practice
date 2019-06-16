package ca.jeonghoon.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtnShowInfo(View view) {
        String name = ((EditText) findViewById(R.id.edtName)).getText().toString();
        String strSex = ((RadioButton) findViewById(R.id.radioFemale)).isChecked() ? "Female" : "Male";

        String strSport;
        if (((RadioButton) findViewById(R.id.radioSoccer)).isChecked()) {
            strSport = "Soccer";
        } else if (((RadioButton) findViewById(R.id.radioHockey)).isChecked()) {
            strSport = "Hockey";
        } else {
            strSport = "Handball";
        }

        Toast.makeText(this,
                String.format("My name is %s.\nI'm a %s.\nMy favorite sport is %s.", name, strSex, strSport),
                Toast.LENGTH_LONG)
                .show();
    }
}
