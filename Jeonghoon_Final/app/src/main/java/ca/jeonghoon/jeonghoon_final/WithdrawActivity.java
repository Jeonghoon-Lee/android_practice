package ca.jeonghoon.jeonghoon_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ca.jeonghoon.jeonghoon_final.model.Account;
import ca.jeonghoon.jeonghoon_final.model.AccountCollection;

public class WithdrawActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewBalance;
    EditText editTextAmount;

    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        initialize();
        getAccountId();

        populateData();
    }

    private void initialize() {
        textViewBalance = findViewById(R.id.textViewBalance);
        editTextAmount = findViewById(R.id.editTextAmount);

        findViewById(R.id.buttonWithdraw).setOnClickListener(this);
    }

    private void getAccountId() {
        int accountId = (int) getIntent().getExtras().get(DetailActivity.EXTRA_ACCOUNT_ID);
        // get person from list
        account = AccountCollection.getAccountList().get(accountId);
    }

    private void populateData() {
        textViewBalance.setText(String.format("%.2f", account.getBalance()));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonWithdraw) {
            if (editTextAmount.getText().length() == 0) {
                Toast.makeText(this, "Pleas enter amount", Toast.LENGTH_LONG).show();
            } else {
                double withdrawAmount = Double.valueOf(editTextAmount.getText().toString());

                if (account.getBalance() > withdrawAmount) {
                    account.setBalance(account.getBalance() - withdrawAmount);
                    populateData();
                } else {
                    Toast.makeText(this, "Withdraw amount can't exceed balance", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
