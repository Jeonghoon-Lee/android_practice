package ca.jeonghoon.jeonghoon_mid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import ca.jeonghoon.jeonghoon_mid.Model.Order;

public class OrderActivity extends AppCompatActivity {

    Order order;

    TextView textViewName, textViewOrder;
    EditText editTextAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initialize();
        getOrderInfo();
        displayOrder();
    }

    private void initialize() {
        textViewName = findViewById(R.id.textViewName);
        textViewOrder = findViewById(R.id.textViewOrder);

        editTextAddress = findViewById(R.id.editTextAddress);

        // add event listener for button
        findViewById(R.id.btnBackToMain).setOnClickListener((view) -> goBackToMain(view));
    }

    private void getOrderInfo() {
        if (getIntent() != null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("orderData");

            // get order
            order = (Order) bundle.getSerializable("order");
        }
    }

    private void displayOrder() {
        textViewName.setText(order.getUserName());
        textViewOrder.setText(order.getOrder());
    }

    void goBackToMain(View view) {
        Toast.makeText(this,
                String.format("Thank you for using our application, %s", order.getUserName()),
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.putExtra("address", editTextAddress.getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }
}
