package ca.jeonghoon.day5passingindent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

import ca.jeonghoon.day5passingindent.model.Client;
import ca.jeonghoon.day5passingindent.model.DataCollection;

public class ShowClientActivity extends AppCompatActivity {

    List<Client> clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_client);

        getMyIntent();
    }

    private void getMyIntent() {

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("intentExtra");

        Serializable bundleContent = bundle.getSerializable("bundleContent");

        DataCollection dataCollection = (DataCollection)bundleContent;

        clientList = dataCollection.getClientList();
    }


}
