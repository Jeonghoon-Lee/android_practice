package ca.jeonghoon.day7component;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ca.jeonghoon.day7component.dialog.DialogActivity;
import ca.jeonghoon.day7component.spinner.SpinningRatingActivity;
import ca.jeonghoon.day7component.toast.ToastActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        findViewById(R.id.btnDialog).setOnClickListener(this);
        findViewById(R.id.btnToast).setOnClickListener(this);
        findViewById(R.id.btnInternet).setOnClickListener(this);
        findViewById(R.id.btnAsync).setOnClickListener(this);
        findViewById(R.id.btnSpinner).setOnClickListener(this);
        findViewById(R.id.btnListView).setOnClickListener(this);
        findViewById(R.id.btnFile).setOnClickListener(this);
        findViewById(R.id.btnDB).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btnDialog:
                intent = new Intent(this, DialogActivity.class);
                break;
            case R.id.btnToast:
                intent = new Intent(this, ToastActivity.class);
                break;
            case R.id.btnInternet:
                break;
            case R.id.btnAsync:
                break;
            case R.id.btnSpinner:
                intent = new Intent(this, SpinningRatingActivity.class);
                break;
            case R.id.btnListView:
                break;
            case R.id.btnFile:
                break;
            case R.id.btnDB:
                break;
        }
        if (intent != null)
            startActivity(intent);
    }
}
