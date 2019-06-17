package ca.jeonghoon.day4project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editName;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        editName = findViewById(R.id.editName);
        btnSubmit = findViewById(R.id.btnSubmit);

        //  Approach 1: Create and Pass an object
//        View.OnClickListener onClickListenerBtnSubmit = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Approach 1: Create and Pass an object",
//                        Toast.LENGTH_LONG).show();
//            }
//        };
//        btnSubmit.setOnClickListener(onClickListenerBtnSubmit);

        // Approach 2:
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Approach 2: Anonymous class",
//                        Toast.LENGTH_LONG).show();
//            }
//        });

        // Approach 3:
//        btnSubmit.setOnClickListener((View v) ->
//            Toast.makeText(MainActivity.this, "Approach 3: Anonymous class",
//                    Toast.LENGTH_LONG).show());

        // Approach 4:
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Approach 4: common way",
                Toast.LENGTH_LONG).show();
    }
}
