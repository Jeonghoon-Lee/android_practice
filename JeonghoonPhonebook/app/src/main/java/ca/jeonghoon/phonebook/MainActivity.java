package ca.jeonghoon.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ca.jeonghoon.phonebook.model.Person;
import ca.jeonghoon.phonebook.model.PersonCollection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        textViewLastName = findViewById(R.id.textViewLastName);

        findViewById(R.id.buttonList).setOnClickListener(this);
        findViewById(R.id.buttonPersonActivity).setOnClickListener(this);

        // initialize dummy data
        initializeDummyData();
    }

    private void initializeDummyData() {
        PersonCollection.getPersonList().add(
                new Person("James", "Smith", R.drawable.pikachu,
                        "5141231234", "james@gmail.com", "Montreal")
        );
        PersonCollection.getPersonList().add(
                new Person("Georgy", "Kim", R.drawable.simpson_300,
                        "5142312346", "kim@gmail.com", "Montreal")
        );
        PersonCollection.getPersonList().add(
                new Person("Alex", "Horton", R.drawable.supermario,
                        "4421231234", "alex@facebook.net", "Toronto")
        );
        PersonCollection.getPersonList().add(
                new Person("Tom", "Doh", R.drawable.tweety,
                        "2149991111", "tom.doh@hotmail.com", "Ottawa")
        );
        PersonCollection.getPersonList().add(
                new Person("Cory", "Jackson", R.drawable.mickeymouse,
                        "2149807312", "cory@gmail.com", "Vancouver")
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonList:
                showPhonebookList();
                break;
            case R.id.buttonPersonActivity:
                openPersonActivity();
                break;
        }
    }

    private void showPhonebookList() {
        Intent intent = new Intent(MainActivity.this, PhonebookActivity.class);
        startActivity(intent);
    }

    private void openPersonActivity() {
        Intent intent = new Intent(MainActivity.this, PersonActivity.class);
        startActivity(intent);
    }
}
