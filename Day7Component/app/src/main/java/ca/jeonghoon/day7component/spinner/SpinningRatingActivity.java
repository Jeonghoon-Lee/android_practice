package ca.jeonghoon.day7component.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ca.jeonghoon.day7component.R;
import ca.jeonghoon.day7component.spinner.Model.MealRating;

public class SpinningRatingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner spinnerMeal;
    ImageView imageViewMeal;
    RatingBar ratingBarMeal;

    String listMeal[] = {"Salmon", "Poutine", "Sushi", "Tacos" };
    int mealPicture[] = {R.drawable.salmon, R.drawable.poutine, R.drawable.sushi, R.drawable.tacos};

    ArrayList<MealRating> listOfMealRating;
    ArrayAdapter<String> mealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinning_rating);

        initialize();
    }

    private void initialize() {
        // initialize spinner
        spinnerMeal = findViewById(R.id.spinnerMeal);
        spinnerMeal.setOnItemSelectedListener(this);

        mealAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                listMeal);
        spinnerMeal.setAdapter(mealAdapter);
        ////

        imageViewMeal = findViewById(R.id.imageViewMeal);
        ratingBarMeal = findViewById(R.id.ratingBarMeal);

        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnShowAll).setOnClickListener(this);

        listOfMealRating = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            addMealRating();
        } else {    // R.id.btnShowAll
            showAllMealRating();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        imageViewMeal.setImageResource(mealPicture[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    private void addMealRating() {
        String meal = spinnerMeal.getSelectedItem().toString();

        double rating = ratingBarMeal.getRating();

        MealRating mealRating = new MealRating(meal, rating);
        listOfMealRating.add(mealRating);

        ratingBarMeal.setRating(0);
    }

    private void showAllMealRating() {
        Collections.sort(listOfMealRating);

        String strList = "";

        for (MealRating item : listOfMealRating) {
            strList = strList + item;
        }
        Toast.makeText(this, strList, Toast.LENGTH_LONG).show();
    }
}
