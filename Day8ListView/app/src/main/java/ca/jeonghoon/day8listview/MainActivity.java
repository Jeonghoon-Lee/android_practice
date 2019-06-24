package ca.jeonghoon.day8listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeListView();
    }

    private void initializeListView() {

        // 1- Create a ListView with XML array entries
        ListView listView = findViewById(R.id.list_options);
        // 1-1- listView Adapter is set in listView XML by an array from strings.xml

        // 2- Create an OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener =

                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listView,
                                            View itemView,
                                            int position,   // Position in the ListView start from zero
                                            long id) {      // Row id of the underlying data
                        if (position == 0) {
                            Intent intent = new Intent(MainActivity.this, DrinkCategoryActivity.class);
                            startActivity(intent);
                        }
                    }
                };

        // 3- Add the listener to the list view
        listView.setOnItemClickListener(itemClickListener);
    }
}
