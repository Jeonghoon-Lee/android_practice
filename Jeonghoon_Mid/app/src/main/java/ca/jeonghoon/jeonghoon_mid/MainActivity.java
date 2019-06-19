package ca.jeonghoon.jeonghoon_mid;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import ca.jeonghoon.jeonghoon_mid.Model.Order;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_ORDER = 1;

    Order order = new Order();

    EditText editTextName;
    RadioGroup radioGroupMenu;
    RadioButton radioBtnPoutine, radioBtnChefPoutine, radioBtnSalmon, radioBtnSushi, radioBtnTacos;
    TextView textViewAddress;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    void initialize() {
        editTextName = findViewById(R.id.editTextName);

        radioGroupMenu = findViewById(R.id.radioGroupMenu);

        radioBtnPoutine = findViewById(R.id.radioBtnPoutine);
        radioBtnPoutine.setOnClickListener(this);

        radioBtnChefPoutine = findViewById(R.id.radioBtnChefPoutine);
        radioBtnChefPoutine.setOnClickListener(this);

        radioBtnSalmon = findViewById(R.id.radioBtnSalmon);
        radioBtnSalmon.setOnClickListener(this);

        radioBtnSushi = findViewById(R.id.radioBtnSushi);
        radioBtnSushi.setOnClickListener(this);

        radioBtnTacos = findViewById(R.id.radioBtnTacos);
        radioBtnTacos.setOnClickListener(this);

        textViewAddress = findViewById(R.id.textViewAddress);

        imageView = findViewById(R.id.imageView);
        // set default image
        imageView.setImageResource(R.drawable.chef_poutine);

        // set event listener for buttons
        findViewById(R.id.btnFinish).setOnClickListener(this);
        findViewById(R.id.btnOrder).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radioBtnPoutine:
                imageView.setImageResource(R.drawable.poutine);
                break;
            case R.id.radioBtnChefPoutine:
                imageView.setImageResource(R.drawable.chef_poutine);
                break;
            case R.id.radioBtnSalmon:
                imageView.setImageResource(R.drawable.salmon);
                break;
            case R.id.radioBtnSushi:
                imageView.setImageResource(R.drawable.sushi);
                break;
            case R.id.radioBtnTacos:
                imageView.setImageResource(R.drawable.tacos);
                break;
            case R.id.btnFinish:
                exitFromApp();
                break;
            case R.id.btnOrder:
                order();
                break;
        }
    }

    private void exitFromApp() {
        finish();
    }

    private void order() {
        String userName = editTextName.getText().toString();
        String selectedMenu = "";

        // get selected menu
        switch (radioGroupMenu.getCheckedRadioButtonId()) {
            case R.id.radioBtnPoutine:
                selectedMenu = radioBtnPoutine.getTag().toString();
                break;
            case R.id.radioBtnChefPoutine:
                selectedMenu = radioBtnChefPoutine.getTag().toString();
                break;
            case R.id.radioBtnSalmon:
                selectedMenu = radioBtnSalmon.getTag().toString();
                break;
            case R.id.radioBtnSushi:
                selectedMenu = radioBtnSushi.getTag().toString();
                break;
            case R.id.radioBtnTacos:
                selectedMenu = radioBtnTacos.getTag().toString();
                break;
        }

        // set order information
        order.setUserName(userName);
        order.setOrder(selectedMenu);

        // call next activity
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", order);

        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("orderData", bundle);

        startActivityForResult(intent, REQUEST_ORDER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_ORDER) {
            String result;
            if (resultCode == RESULT_OK) {
                result = data.getStringExtra("address");
                order.setUserAdderss(result);
            } else {
                // prevent error
                result = data != null ? data.getStringExtra("address") : "";
            }
            textViewAddress.setText(result);
        }
    }
}
