package ca.jeonghoon.day5applifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("---- 1. onCreate ----");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("---- 2. onStart ----");
        Log.d("log_test", "my log");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("---- 1-2 onRestart ----");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("---- 5. onStop ----");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("---- 6. onDestroy ----");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("---- 3. onResume ----");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("---- 4. onPause ----");
    }

}
