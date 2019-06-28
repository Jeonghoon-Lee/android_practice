package ca.masoudbozorgi.customlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ca.masoudbozorgi.customlist.xml_json.JSONParser;
import ca.masoudbozorgi.customlist.xml_json.XMLParser;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {

    Button jsonReaderBtn, xmlReaderBtn, readerDB;

    ArrayList<Flower> flowerArrayList;
    ListView advanced_listView;
    CellController_BaseAdapter cellController_base_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        initializeModel();
        initializeCustomList();
    }

    private void initialize() {

        jsonReaderBtn = findViewById(R.id.jsonReaderBtn);
        jsonReaderBtn.setOnClickListener(this);

        xmlReaderBtn = findViewById(R.id.xmlReaderBtn);
        xmlReaderBtn.setOnClickListener(this);

        readerDB = findViewById(R.id.readerDB);
        readerDB.setOnClickListener(this);
    }


    private void initializeModel() {

        flowerArrayList = new ArrayList<>();

        Flower modelItem_0 = new Flower(2391, "cat0", "name0_db", "Some instruction for test reading fron DB 0", 10, "california_snow.jpg");
        Flower modelItem_1 = new Flower(2391, "cat1", "name1_db", "Some instruction for test reading fron DB 1", 11, "princess_flower.jpg");
        Flower modelItem_2 = new Flower(2392, "cat2", "name2_db", "Some instruction for test reading fron DB 2", 12, "haight_ashbury.jpg");
        Flower modelItem_3 = new Flower(2393, "cat3", "name3_db", "Some instruction for test reading fron DB 3", 13, "mona_lavender.jpg");
        Flower modelItem_4 = new Flower(2394, "cat4", "name4_db", "Some instruction for test reading fron DB 4", 14, "camellia.jpg");
        Flower modelItem_5 = new Flower(2395, "cat5", "name5_db", "Some instruction for test reading fron DB 5", 15, "bougainvillea.jpg");
        Flower modelItem_6 = new Flower(2396, "cat6", "name6_db", "Some instruction for test reading fron DB 6", 16, "rosa_burgundy.jpg");
        Flower modelItem_7 = new Flower(2397, "cat7", "name7_db", "Some instruction for test reading fron DB 7", 17, "rosa_iceberg.jpg");
        Flower modelItem_8 = new Flower(2398, "cat8", "name8_db", "Some instruction for test reading fron DB 8", 18, "bonsai.jpg");
        Flower modelItem_9 = new Flower(2399, "cat9", "name9_db", "Some instruction for test reading fron DB 9", 19, "calibrachoa.jpg");

        flowerArrayList.add(modelItem_0);
        flowerArrayList.add(modelItem_1);
        flowerArrayList.add(modelItem_2);
        flowerArrayList.add(modelItem_3);
        flowerArrayList.add(modelItem_4);
        flowerArrayList.add(modelItem_5);
        flowerArrayList.add(modelItem_6);
        flowerArrayList.add(modelItem_7);
        flowerArrayList.add(modelItem_8);
        flowerArrayList.add(modelItem_9);
    }


    private void initializeCustomList() {
        advanced_listView = findViewById(R.id.advanced_listView);
        advanced_listView.setOnItemClickListener(this);
        advanced_listView.setOnItemLongClickListener(this);

        cellController_base_adapter = new CellController_BaseAdapter(this, flowerArrayList);
        advanced_listView.setAdapter(cellController_base_adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.jsonReaderBtn:
                JSONParser json_parser = new JSONParser();
                flowerArrayList = json_parser.processJSONFile(this, "flowers_json.json");
                break;

            case R.id.xmlReaderBtn:
                XMLParser xml_parser = new XMLParser(this);
                flowerArrayList = xml_parser.parseXML();
                break;

            case R.id.readerDB:

                break;
        }

        cellController_base_adapter = new CellController_BaseAdapter(this, flowerArrayList);
        advanced_listView.setAdapter(cellController_base_adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(this, DetailActivity.class));
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        showAlertDialog(view);

        // true means that the View that currently received the event is the true event receiver and
        // the event should not be propagated to the other Views in the tree;
        // when you return false you let the event be passed to the other Views that may consume it.

        // return true to prevent conflict with onItemClick
        return true;
    }

    public void showAlertDialog(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // type 2
        builder.setTitle("AlertDialog\njust a question for demo")
                .setMessage("Do you want to delete this file?")
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_info)

                // We just define event listener for yes button,
                // but it can be defined for 'No' and 'Cancel' as well
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "It was just a test!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .setNeutralButton("Cancel", null);

        builder.show();
    }
}