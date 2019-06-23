package ca.jeonghoon.jeonghoonproject;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import ca.jeonghoon.jeonghoonproject.Model.MathOperator;
import ca.jeonghoon.jeonghoonproject.Model.MathQuiz;
import ca.jeonghoon.jeonghoonproject.Model.MathQuizCollection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static final int REQUEST_SHOW_RESULT = 1;

    EditText editTextAnswer;

    TextView textViewTitle;
    TextView textViewQuiz;

    private MathOperator operator;
    private int operand1;
    private int operand2;

    StringBuilder userInputStr;

    MathQuizCollection mathQuizCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        editTextAnswer = findViewById(R.id.editTextAnswer);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewQuiz = findViewById(R.id.textViewQuiz);

        // Add onclick event listeners for buttons
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.buttonDot).setOnClickListener(this);
        findViewById(R.id.buttonMinus).setOnClickListener(this);

        findViewById(R.id.buttonGenerate).setOnClickListener(this);
        findViewById(R.id.buttonValidate).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);

        findViewById(R.id.buttonScore).setOnClickListener(this);
        findViewById(R.id.buttonFinish).setOnClickListener(this);

        // initialize data collection
        mathQuizCollection = new MathQuizCollection();
        // initialize user input
        userInputStr = new StringBuilder();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
            case R.id.button0:
            case R.id.buttonDot:
            case R.id.buttonMinus:
                processUserInput(view);
                break;

            case R.id.buttonGenerate:
                generateQuiz();
                break;
            case R.id.buttonValidate:
                validateAnswer();
                break;
            case R.id.buttonClear:
                clearDisplay();
                break;

            case R.id.buttonScore:
                score();
                break;
            case R.id.buttonFinish:
                finish();
                break;
        }
    }

    private void addNumberToUserAnswer(String input) {
        if (userInputStr.length() == 0) {       // empty
            if (input.equals(".")) {
                userInputStr.append("0");       // add 0 before adding dot(.)
            }
            userInputStr.append(input);

            // enable validate button
            findViewById(R.id.buttonValidate).setEnabled(true);
        } else {
            if (userInputStr.length() == 1 && userInputStr.indexOf("0") == 0) { // current input is 0
                // number can't be started with 0. ignore previous 0
                if (!input.equals(".")) {
                    userInputStr.setLength(0);      // clear buffer
                }
                userInputStr.append(input);
            } else {
                if (input.equals("-")) {
                    if (userInputStr.indexOf("-") == 0) {   // number is already minus value
                        userInputStr.deleteCharAt(0);       // delete minus(-) sign
                    } else {
                        userInputStr.insert(0, "-");
                    }
                } else if (input.equals(".")) {
                    if (userInputStr.indexOf(".") < 0) {   // if number doesn't have dot(.)
                        userInputStr.append(".");
                    }
                } else {    // number
                    userInputStr.append(input);
                }
            }
        }
    }

    private void processUserInput(View view) {
        switch (view.getId()) {
            case R.id.button1:
                addNumberToUserAnswer("1");
                break;
            case R.id.button2:
                addNumberToUserAnswer("2");
                break;
            case R.id.button3:
                addNumberToUserAnswer("3");
                break;
            case R.id.button4:
                addNumberToUserAnswer("4");
                break;
            case R.id.button5:
                addNumberToUserAnswer("5");
                break;
            case R.id.button6:
                addNumberToUserAnswer("6");
                break;
            case R.id.button7:
                addNumberToUserAnswer("7");
                break;
            case R.id.button8:
                addNumberToUserAnswer("8");
                break;
            case R.id.button9:
                addNumberToUserAnswer("9");
                break;
            case R.id.button0:
                addNumberToUserAnswer("0");
                break;
            case R.id.buttonDot:
                addNumberToUserAnswer(".");
                break;
            case R.id.buttonMinus:
                addNumberToUserAnswer("-");
                break;
        }
        editTextAnswer.setText(userInputStr);
        editTextAnswer.moveCursorToVisibleOffset();
    }

    private void generateQuiz() {
        Random rand = new Random();

        operator = MathOperator.getValueFromInt(rand.nextInt(4));
        operand1 = rand.nextInt(21) - 10;   // generator -10 ~ 10
        operand2 = rand.nextInt(21) - 10;   // generator -10 ~ 10

        String quiz = "";
        switch (operator) {
            case Add:
                quiz = String.format("%d + %d", operand1, operand2);
                break;
            case Subtract:
                quiz = String.format("%d - %d", operand1, operand2);
                break;
            case Multiply:
                quiz = String.format("%d * %d", operand1, operand2);
                break;
            case Divide:
                if (operand2 == 0)      // prevent error (divide by 0)
                    operand2++;
                quiz = String.format("%d / %d", operand1, operand2);
                break;
        }
        // display quiz and clear answer input
        textViewQuiz.setText(quiz);
        clearDisplay();
    }

    private void validateAnswer() {
        MathQuiz mathQuizAnswer = new MathQuiz(operator, operand1, operand2);

        // set user Answer to currentQuiz
        mathQuizAnswer.setUserAnswer(userInputStr.toString());

        // Add to the mathQuizCollection
        mathQuizCollection.getQuizList().add(mathQuizAnswer);

        // show message to user
        if (mathQuizAnswer.isValidAnswer()) {
            Toast.makeText(this, "right", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "wrong", Toast.LENGTH_LONG).show();
        }
    }

    private void clearDisplay() {
        userInputStr.setLength(0);
        editTextAnswer.setText(userInputStr);

        // disable validate button
        findViewById(R.id.buttonValidate).setEnabled(false);
    }

    private void score() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("mathQuizList", mathQuizCollection);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("data", bundle);

        startActivityForResult(intent, REQUEST_SHOW_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if ((requestCode == REQUEST_SHOW_RESULT) && (data != null)) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                // display register name and score in the title
                textViewTitle.setText(String.format("%s, %d%%", name, mathQuizCollection.getScore()));
            }
        }
    }
}
