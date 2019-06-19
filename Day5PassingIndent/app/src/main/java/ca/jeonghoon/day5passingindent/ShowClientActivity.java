package ca.jeonghoon.day5passingindent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import ca.jeonghoon.day5passingindent.model.Client;
import ca.jeonghoon.day5passingindent.model.DataCollection;

public class ShowClientActivity extends AppCompatActivity {

    RadioButton radioAdventure, radioAction, radioComedy, radioAll;

    List<Client> clientList;
    String strResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_client);

        initialize();
        getMyIntent();
    }

    private void initialize() {
        // RadioGroup
        radioAdventure = findViewById(R.id.radioAdventure);
        radioAction = findViewById(R.id.radioAction);
        radioComedy = findViewById(R.id.radioComedy);
        radioAll = findViewById(R.id.radioAll);

        findViewById(R.id.btnList).setOnClickListener(view -> showListOfClients(view));
    }

    private void getMyIntent() {

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("intentExtra");

        Serializable bundleContent = bundle.getSerializable("bundleContent");

        DataCollection dataCollection = (DataCollection)bundleContent;

        clientList = dataCollection.getClientList();
    }

    public void showListOfClients(View view) {

        if (radioAdventure.isChecked())
            iterateByType("adv");

        else if (radioAction.isChecked())
            iterateByType("action");

        else if (radioComedy.isChecked())
            iterateByType("comedy");

        else if (radioAll.isChecked())
            iterateByType("all");

        ((TextView) findViewById(R.id.textViewResult)).setText(strResult);
    }


    void iterateByType(String myType) {
        strResult = "";
        for (Client client : clientList) {
            if (myType.equals("all")) {
                strResult = strResult + client;
            } else if (client.getMovieType().equals(myType)) {
                strResult = strResult + client;
            }
        }
    }
}
