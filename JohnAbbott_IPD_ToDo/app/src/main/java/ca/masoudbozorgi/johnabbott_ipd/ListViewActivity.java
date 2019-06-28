package ca.masoudbozorgi.johnabbott_ipd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListViewActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initialize();
    }

    private void initialize() {

        listView = findViewById(R.id.listView);

        arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("item 1", "Item 2", "Item 3", "Item 4"));

        initializeListView();
    }

    private void initializeListView() {

        // 1- Define an ArrayAdapter
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                arrayList );

        // 2- Set ArrayAdapter for List
        listView.setAdapter(stringArrayAdapter);
    }
}
