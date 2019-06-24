package ca.jeonghoon.day8listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import ca.jeonghoon.day8listview.model.Drink;

public class DrinkCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        initializeListView();
    }

    private void initializeListView() {

        // 1- Initialize ListView
        ListView listDrinks = findViewById(R.id.list_drinks);

        // 2- Create an Adapter for ListView
        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Drink.drinks);

        // 3- Assign the Adapter to the list view
        listDrinks.setAdapter(listAdapter);


        // 4- Create a listener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listDrinksAdapterView,
                                            View itemView,
                                            int position,    // Position in the ListView start from zero
                                            long id) {       // Row id of the underlying data

                        // How to get an item based on clicked cell from AdapterView
                        System.out.println("DrinkCategoryActivity -----------------------\n" +
                                listDrinksAdapterView.getItemAtPosition(position).toString());

                        Toast.makeText(DrinkCategoryActivity.this, "position = " +
                                position + ", id = " + id,Toast.LENGTH_SHORT).show();

                        // 3-1- Pass the drink the user clicks on to DrinkActivity
                        Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
                        intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int) id);
                        startActivity(intent);
                    }
                };

        // 5- Assign the listener to the list view
        listDrinks.setOnItemClickListener(itemClickListener);
    }
}
