package ca.jeonghoon.jeonghoon_final;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import ca.jeonghoon.jeonghoon_final.model.Account;
import ca.jeonghoon.jeonghoon_final.model.AccountCollection;
import ca.jeonghoon.jeonghoon_final.model.Customer;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_ACCOUNT_ID = "accountId";

    EditText editTextAccountNo, editTextOpenDate, editTextBalance;
    EditText editTextFirstName, editTextLastName, editTextPhone, editTextSIN;

    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initialize();
        getAccountId();
    }

    private void initialize() {
        editTextAccountNo = findViewById(R.id.editTextAccountNo);
        editTextOpenDate = findViewById(R.id.editTextOpenDate);
        editTextBalance = findViewById(R.id.editTextBalance);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextSIN = findViewById(R.id.editTextSIN);

        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonFind).setOnClickListener(this);
        findViewById(R.id.buttonRemove).setOnClickListener(this);
        findViewById(R.id.buttonUpdate).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonShowAll).setOnClickListener(this);
    }

    private void getAccountId() {
        int accountId = (int) getIntent().getExtras().get(EXTRA_ACCOUNT_ID);

        if (accountId == -1) {
            findViewById(R.id.buttonAdd).setEnabled(true);
        } else {
            // get person from list
            account = AccountCollection.getAccountList().get(accountId);

            populateData();
        }
    }

    private void populateData() {
        editTextAccountNo.setText(account.getAccountNumber());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = formatter.format(account.getOpenDate());
        editTextOpenDate.setText(strDate);

        editTextBalance.setText(String.format("%.2f", account.getBalance()));

        editTextFirstName.setText(account.getAccountHolder().getFirstName());
        editTextLastName.setText(account.getAccountHolder().getLastName());
        editTextPhone.setText(account.getAccountHolder().getPhone());
        editTextSIN.setText(account.getAccountHolder().getSin());
    }

    private void clearData() {
        editTextAccountNo.setText("");
        editTextOpenDate.setText("");
        editTextBalance.setText("");

        editTextFirstName.setText("");
        editTextLastName.setText("");
        editTextPhone.setText("");
        editTextSIN.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAdd:
                AddAccount();
                break;
            case R.id.buttonFind:
                findAccountBySIN();
                break;
            case R.id.buttonRemove:
                removeAccountBySIN();
                break;
            case R.id.buttonUpdate:
                updateAccountBySIN();
                break;
            case R.id.buttonClear:
                clearData();
                break;
            case R.id.buttonShowAll:
                showAll();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void AddAccount() {
        try {
            String accountNo = editTextAccountNo.getText().toString();
            String openDateStr = editTextOpenDate.getText().toString();
            String balanceStr = editTextBalance.getText().toString();

            String firstName = editTextFirstName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            String phone = editTextPhone.getText().toString();
            String sin = editTextSIN.getText().toString();

            if (accountNo.length() == 0 ||
                    openDateStr.length() == 0 ||
                    balanceStr.length() == 0 ||
                    firstName.length() == 0 ||
                    lastName.length() == 0 ||
                    phone.length() == 0 ||
                    sin.length() == 0) {
                Toast.makeText(this, "Please enter the all of fields!", Toast.LENGTH_LONG).show();
                return;
            }

            if (AccountCollection.getAccountList().stream()
                    .filter(account -> account.getAccountNumber().equals(accountNo)).count() > 0) {
                Toast.makeText(this, "Account number is already registered.\nPlease change account number", Toast.LENGTH_LONG).show();
                return;
            }

            Date openDate = new SimpleDateFormat("yyyy/MM/dd").parse(openDateStr);

            AccountCollection.getAccountList().add(
                    new Account(accountNo, openDate, Double.valueOf(balanceStr),
                            new Customer(firstName, lastName, phone, sin))
            );

            Toast.makeText(this, "Account was successfully added.", Toast.LENGTH_LONG).show();
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "Pleas check date format", Toast.LENGTH_LONG).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void findAccountBySIN() {
        String sin = editTextSIN.getText().toString();

        if (sin.length() == 0) {
            Toast.makeText(this, "Pleas enter SIN number", Toast.LENGTH_LONG).show();
        }

        Optional<Account> foundedAccount = AccountCollection.getAccountList().stream()
                .filter(account -> account.getAccountHolder().getSin().equals(sin)).findFirst();

        if (foundedAccount.isPresent()) {
            account = foundedAccount.get();
            populateData();
        } else {
            clearData();
            Toast.makeText(this, "Not registered SIN", Toast.LENGTH_LONG).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void removeAccountBySIN() {
        String sin = editTextSIN.getText().toString();

        if (sin.length() == 0) {
            Toast.makeText(this, "Pleas enter SIN number", Toast.LENGTH_LONG).show();
        }

        Optional<Account> foundedAccount = AccountCollection.getAccountList().stream()
                .filter(account -> account.getAccountHolder().getSin().equals(sin)).findFirst();

        if (foundedAccount.isPresent()) {
            account = foundedAccount.get();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Remove a account")
                    .setMessage("Do you want to delete this account ?\n\n" + account)
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_delete)

                    .setPositiveButton("Yes", (dialog, i) -> {
                        AccountCollection.getAccountList().remove(account);
                        Toast.makeText(this, "Account was successfully removed", Toast.LENGTH_LONG).show();
                        clearData();
                    })
                    .setNegativeButton("No", null);
            builder.show();
        } else {
            Toast.makeText(this, "Not registered SIN", Toast.LENGTH_LONG).show();
            clearData();
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void updateAccountBySIN() {
        String sin = editTextSIN.getText().toString();

        if (sin.length() == 0) {
            Toast.makeText(this, "Pleas enter SIN number", Toast.LENGTH_LONG).show();
        }

        Optional<Account> foundedAccount = AccountCollection.getAccountList().stream()
                .filter(account -> account.getAccountHolder().getSin().equals(sin)).findFirst();

        if (foundedAccount.isPresent()) {
            account = foundedAccount.get();

            // update account information
            String accountNo = editTextAccountNo.getText().toString();
            String openDateStr = editTextOpenDate.getText().toString();
            String balanceStr = editTextBalance.getText().toString();

            String firstName = editTextFirstName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            String phone = editTextPhone.getText().toString();

            if (accountNo.length() == 0 ||
                    openDateStr.length() == 0 ||
                    balanceStr.length() == 0 ||
                    firstName.length() == 0 ||
                    lastName.length() == 0 ||
                    phone.length() == 0) {
                Toast.makeText(this, "Please enter the all of fields!", Toast.LENGTH_LONG).show();
                return;
            }

            if (AccountCollection.getAccountList().stream()
                    .filter(account -> account.getAccountNumber().equals(accountNo) && !account.getAccountHolder().getSin().equals(sin)).count() > 0) {
                Toast.makeText(this, "Account number is already registered.\nPlease change account number", Toast.LENGTH_LONG).show();
                return;
            }

            try {
                Date openDate = new SimpleDateFormat("yyyy/MM/dd").parse(openDateStr);

                account.setAccountNumber(accountNo);
                account.setOpenDate(openDate);
                account.setBalance(Double.valueOf(balanceStr));

                account.getAccountHolder().setFirstName(firstName);
                account.getAccountHolder().setLastName(lastName);
                account.getAccountHolder().setPhone(phone);
                account.getAccountHolder().setSin(sin);
            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(this, "Pleas check date format", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Not registered SIN", Toast.LENGTH_LONG).show();
        }
    }

    private void showAll() {
        setResult(RESULT_OK, new Intent());
        finish();
    }
}
