package ca.jeonghoon.day7component.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ca.jeonghoon.day7component.R;

public class ToastActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        initialize();
    }

    private void initialize() {
        findViewById(R.id.btnToast1).setOnClickListener(this);
        findViewById(R.id.btnToast2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnToast1:
                ToastBuilder.alert(this, "test", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnToast2:
                ToastBuilder.imageToast(this, R.drawable.bird, Toast.LENGTH_LONG).show();
                break;
            case R.id.btnToast3:
                break;
            case R.id.btnToast4:
                break;
            case R.id.btnToast5:
                break;
            case R.id.btnToast6:
                break;
            case R.id.btnToast7:
                break;
        }
    }
}
