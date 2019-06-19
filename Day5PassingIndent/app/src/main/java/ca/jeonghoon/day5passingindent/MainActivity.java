package ca.jeonghoon.day5passingindent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ca.jeonghoon.day5passingindent.model.Client;
import ca.jeonghoon.day5passingindent.model.DataCollection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText textClientId;
    EditText textClientEmail;
    RadioGroup radioGrMovieType;

    RadioButton radioBtnAdventure;
    RadioButton radioBtnAction;
    RadioButton radioBtnComedy;

    DataCollection dataCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        textClientId = (EditText) findViewById(R.id.editClientNumber);
        textClientEmail = (EditText) findViewById(R.id.editClientEmail);
        radioGrMovieType = (RadioGroup) findViewById(R.id.radioGrMovieType);

        radioBtnAdventure = findViewById(R.id.radioAdventure);
        radioBtnAction = findViewById(R.id.radioAction);
        radioBtnComedy = findViewById(R.id.radioComedy);

        // Add event listener for buttons
        findViewById(R.id.btnClear).setOnClickListener(this);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnRemove).setOnClickListener(this);
        findViewById(R.id.btnUpdateEmail).setOnClickListener(this);
        findViewById(R.id.btnShowAll).setOnClickListener(this);

        dataCollection = new DataCollection();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClear:
                clearInput();
                break;
            case R.id.btnAdd:
                addClient();
                break;
            case R.id.btnRemove:
                removeClient();
                break;
            case R.id.btnUpdateEmail:
                updateEmail();
                break;
            case R.id.btnShowAll:
                showAll();
                break;
        }
    }

    private void clearInput() {
        textClientId.setText("");
        textClientEmail.setText("");
        radioGrMovieType.clearCheck();
    }

    private void addClient() {
        int id = Integer.valueOf(textClientId.getText().toString());
        String email = textClientEmail.getText().toString();
        String selectedMovieType = "";

        switch (radioGrMovieType.getCheckedRadioButtonId()) {
            case R.id.radioAnimation:
                selectedMovieType = radioBtnAdventure.getTag().toString();
                break;
            case R.id.radioAction:
                selectedMovieType = radioBtnAction.getTag().toString();
                break;
            case R.id.radioComedy:
                selectedMovieType = radioBtnComedy.getTag().toString();
                break;
        }

        Client newClient = new Client(id, email, selectedMovieType);
        dataCollection.getClientList().add(newClient);

        Toast.makeText(this, newClient.getId() + " added successfully", Toast.LENGTH_LONG).show();
    }

    private void removeClient() {
        if (findClient() >= 0) {
            dataCollection.getClientList().remove(findClient());
            Toast.makeText(this, "Client removed", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateEmail() {
        if (findClient() >= 0) {
            dataCollection.getClientList().get(findClient()).setEmail(textClientEmail.getText().toString());
            Toast.makeText(this, "Client email updated", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAll() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundleContent", dataCollection);

        Intent intent = new Intent(this, ShowClientActivity.class);

        intent.putExtra("intentExtra", bundle);

        startActivity(intent);
    }

    private int findClient() {
        int clientNumber = Integer.valueOf(textClientId.getText().toString());

        for (Client client : dataCollection.getClientList()) {
            if (client.getId() == clientNumber) {
                return dataCollection.getClientList().indexOf(client);
            }
        }
        Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();

        return -1;
    }
}
