package ca.jeonghoon.jeonghoon_final;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

import ca.jeonghoon.jeonghoon_final.model.Account;
import ca.jeonghoon.jeonghoon_final.model.AccountCollection;
import ca.jeonghoon.jeonghoon_final.model.Customer;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {

    private static final int REQ_CODE_SHOWALL = 1234;

    CustomAccount_BaseAdapter customList_adapter;
    ListView listViewAccount;

    ImageButton imageButtonAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        initializeModel();
        initializeCustomList();
    }

    private void initialize() {
        imageButtonAddUser = findViewById(R.id.imageButtonAddUser);
        imageButtonAddUser.setImageResource(R.drawable.add_user);
        imageButtonAddUser.setOnClickListener(this);
    }

    private void initializeModel() {
        try {
            AccountCollection.getAccountList().add(new Account("1234567890",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-31"),
                    100.0,
                    new Customer("John", "Smith", "5141112222", "934333112")));

            AccountCollection.getAccountList().add(new Account("7821568764",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-08"),
                    100.0,
                    new Customer("Maria", "Kim", "5143321234", "123434201")));

            AccountCollection.getAccountList().add(new Account("3987054321",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-31"),
                    100.0,
                    new Customer("Cory", "Damar", "4381233555", "888222333")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @TargetApi(Build.VERSION_CODES.N)
    private void initializeCustomList() {
        listViewAccount = findViewById(R.id.listViewAccount);
        listViewAccount.setOnItemClickListener(this);
        listViewAccount.setOnItemLongClickListener(this);

        customList_adapter = new CustomAccount_BaseAdapter(this, AccountCollection.getAccountList());

        listViewAccount.setAdapter(customList_adapter);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButtonAddUser) {
            // Pass the person id on to DetailActivity
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_ACCOUNT_ID, -1);
            startActivityForResult(intent, REQ_CODE_SHOWALL);
        }

        customList_adapter = new CustomAccount_BaseAdapter(this, AccountCollection.getAccountList());
        listViewAccount.setAdapter(customList_adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // get account data from AdapterView
        Account account = (Account) customList_adapter.getItem((int) id);

        // get selected index from person list
        int selectedPersonId = AccountCollection.getAccountList().indexOf(account);

        // Pass the person id on to DetailActivity
        Intent intent = new Intent(this, WithdrawActivity.class);
        intent.putExtra(DetailActivity.EXTRA_ACCOUNT_ID, selectedPersonId);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // get account data from AdapterView
        Account account = (Account) customList_adapter.getItem((int) id);

        // get selected index from person list
        int selectedPersonId = AccountCollection.getAccountList().indexOf(account);

        // Pass the person id on to DetailActivity
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_ACCOUNT_ID, selectedPersonId);
        startActivityForResult(intent, REQ_CODE_SHOWALL);

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_CODE_SHOWALL) {
            if (resultCode == RESULT_OK) {
                initializeCustomList();
            }
        }
    }
}
