package ca.jeonghoon.day2project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        imageView = findViewById(R.id.imageView);
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void changeImage(View view) {
//        if (!((RadioButton)view).isChecked())
//            return;
//
        switch (view.getId()) {
            case R.id.rbBird:
                imageView.setImageResource(R.drawable.bird);
                break;
            case R.id.rbMan:
                imageView.setImageResource(R.drawable.man);
                break;
            case R.id.rbSimpson:
                imageView.setImageResource(R.drawable.simpson);
                break;
        }

//        switch (radioGroup.getCheckedRadioButtonId()) {
//            case R.id.rbBird:
//                imageView.setImageResource(R.drawable.bird);
//                break;
//            case R.id.rbMan:
//                imageView.setImageResource(R.drawable.man);
//                break;
//            case R.id.rbSimpson:
//                imageView.setImageResource(R.drawable.simpson);
//                break;
//        }
    }
}
