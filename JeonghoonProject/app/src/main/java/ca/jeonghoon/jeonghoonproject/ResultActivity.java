package ca.jeonghoon.jeonghoonproject;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Comparator;

import ca.jeonghoon.jeonghoonproject.Model.MathQuiz;
import ca.jeonghoon.jeonghoonproject.Model.MathQuizCollection;

public class ResultActivity extends AppCompatActivity implements ViewStub.OnClickListener {

    // get data from MainActivity using Intent
    MathQuizCollection mathQuizCollection;

    RadioGroup radioGroupShow;
    EditText editTextName;
    TextView textViewScore;

    ListView listViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initialize();
        getQuizData();
        displayScore();
    }

    private void initialize() {
        radioGroupShow = findViewById(R.id.radioGroupShow);
        editTextName = findViewById(R.id.editTextName);
        textViewScore = findViewById(R.id.textViewScore);

        listViewResult = findViewById(R.id.listViewResult);

        findViewById(R.id.buttonShow).setOnClickListener(this);
        findViewById(R.id.buttonBack).setOnClickListener(this);
    }

    // get a quiz list from MainActivity
    private void getQuizData() {
        Intent intent = getIntent();

        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("data");
            mathQuizCollection = (MathQuizCollection) bundle.getSerializable("mathQuizList");
        }
    }

    private void displayScore() {
        if (mathQuizCollection != null) {
            textViewScore.setText(String.format("%d%%", mathQuizCollection.getScore()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonShow:
                showResult();
                break;
            case R.id.buttonBack:
                backToMain();
                break;
        }
    }

    private void showResult() {
        switch (radioGroupShow.getCheckedRadioButtonId()) {
            case R.id.radioButtonAll:
                showAllResult();
                break;
            case R.id.radioButtonRight:
                showRightResult();
                break;
            case R.id.radioButtonWrong:
                showWrongResult();
                break;
            case R.id.radioButtonSortA:
                showResultByAscending();
                break;
            case R.id.radioButtonSortB:
                showResultByDescending();
                break;
        }
    }

    private void showAllResult() {
        displayList(mathQuizCollection.getQuizList().toArray());
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void showRightResult() {
        displayList(mathQuizCollection.getQuizList().stream()
                .filter(MathQuiz::isValidAnswer)
                .toArray()
        );
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void showWrongResult() {
        displayList(mathQuizCollection.getQuizList().stream()
                .filter(quiz -> !quiz.isValidAnswer())
                .toArray()
        );
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void showResultByAscending() {
        displayList(mathQuizCollection.getQuizList().stream()
                .sorted(Comparator.comparing(MathQuiz::isValidAnswer).reversed())
                .toArray()
        );
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void showResultByDescending() {
        displayList(mathQuizCollection.getQuizList().stream()
                .sorted(Comparator.comparing(MathQuiz::isValidAnswer))
                .toArray()
        );
    }

    // display list data into list view
    private <T>  void displayList(T[] quizArray) {
        ArrayAdapter<MathQuiz> listAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                quizArray
        );

        listViewResult.setAdapter(listAdapter);
    }

    // return registered name to MainActivity
    private void backToMain() {
        if (editTextName.getText().length() > 0) {
            Intent intent = new Intent();
            intent.putExtra("name", editTextName.getText().toString());

            setResult(RESULT_OK, intent);
            finish();
        } else {
            showAlertDialog();
        }
    }

    void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Go back to Main")
                .setMessage("Do you want to go back to main without name ?")
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_info)

                .setPositiveButton("Yes", (dialog, i) -> {
                    setResult(RESULT_CANCELED, null);
                    finish();
                })
                .setNegativeButton("No", null);

        builder.show();
    }
}
