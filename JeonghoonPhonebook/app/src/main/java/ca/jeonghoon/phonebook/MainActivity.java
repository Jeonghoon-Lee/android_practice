package ca.jeonghoon.phonebook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ca.jeonghoon.phonebook.model.Person;
import ca.jeonghoon.phonebook.model.PersonCollection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSIONS_REQ_CODE = 1234;

    TextView textViewLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        // For Android 6 (Marshmallow, API 23)and above we have to check permission in runtime
        // only using AndroidManifest.xml is not enough
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            checkPermissions();
        }
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
                showPhoneBookList();
                break;
            case R.id.buttonPersonActivity:
                openPersonActivity();
                break;
        }
    }

    private void showPhoneBookList() {
        Intent intent = new Intent(MainActivity.this, PhonebookActivity.class);
        startActivity(intent);
    }

    private void openPersonActivity() {
        Intent intent = new Intent(MainActivity.this, PersonActivity.class);
        startActivity(intent);
    }

    private void checkPermissions() {
        if (!hasPermissionForCallEvent()) {
            // Define an array to save the permissions we need for
            // phone state and outgoing calls event
            String[] perms = new String[]{
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS};

            // Request permission with specific code
            ActivityCompat.requestPermissions(this, perms, PERMISSIONS_REQ_CODE);
        }
    }

    private boolean hasPermissionForCallEvent() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED &&

                ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                        == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Check if the permission response is for our request code
        if (requestCode == PERMISSIONS_REQ_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
