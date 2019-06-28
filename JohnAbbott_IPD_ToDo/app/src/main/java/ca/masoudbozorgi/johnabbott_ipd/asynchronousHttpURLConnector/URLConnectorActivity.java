package ca.masoudbozorgi.johnabbott_ipd.asynchronousHttpURLConnector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ca.masoudbozorgi.johnabbott_ipd.R;

public class URLConnectorActivity extends AppCompatActivity implements
        View.OnClickListener,
        HttpURLConnectionPostProcessorInterface {

    TextView textViewData, textViewProgress;
    Button btnLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlconnector);

        initialize();
    }


    private void initialize() {

        textViewData = findViewById(R.id.textViewData);
        textViewProgress = findViewById(R.id.textViewProgress);

        btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String muURLString = "https://www.w3schools.com/xml/simple.xml";

        // 1- Create Async Task object
        AsynchronousHttpURLConnector asynchronousHttpURLConnector =
                new AsynchronousHttpURLConnector(
                        this,
                        muURLString,
                        textViewProgress);

        // 2- Will run in background
        asynchronousHttpURLConnector.execute();
    }

    @Override
    public void successHandler(String dataInXML) {
        System.out.println("-------------------- successHandler: " + dataInXML);
        textViewData.setText(dataInXML);
    }

    @Override
    public void failureHandler(Exception exception) {
        System.out.println("-------------------- failureHandler ");
    }
}
