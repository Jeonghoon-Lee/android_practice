package ca.jeonghoon.day7component.internet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import ca.jeonghoon.day7component.R;

public class InternetActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
    Button btnLoad;

    String url = "https://www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        initialize();
    }

    void initialize() {
        btnLoad = findViewById(R.id.buttonLoad);
        btnLoad.setOnClickListener(this);

        webView = findViewById(R.id.webView);
    }

    @Override
    public void onClick(View v) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
