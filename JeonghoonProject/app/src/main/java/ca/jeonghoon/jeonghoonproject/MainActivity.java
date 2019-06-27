package ca.jeonghoon.jeonghoonproject;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    MathQuizCollection mathQuizCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewQuiz = findViewById(R.id.textViewQuiz);

        editTextAnswer = findViewById(R.id.editTextAnswer);

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
                exitProgram();
                break;
        }
    }

    addNumberToUserAnswer(String input) {
        StringBuilder currentAnswer = new StringBuilder(editTextAnswer.getText().toString());

        // enable validate button
        findViewById(R.id.buttonValidate).setEnabled(true);

        if (input.equals("-")) {
            if (currentAnswer.indexOf("-") == 0) {
                // number is already minus value
                currentAnswer.deleteCharAt(0);       // delete minus(-) sign
            } else {
                currentAnswer.insert(0, "-");
            }
        } else if (input.equals(".")) {
            if (currentAnswer.length() == 0) {
                currentAnswer.append("0.");
            } else if (currentAnswer.length() == 1 && currentAnswer.indexOf("-") == 0) {
                // current input is - sign
                currentAnswer.append("0.");
            } else if (currentAnswer.indexOf(".") < 0) {
                // dot(.) is not a part of user input
                currentAnswer.append(".");
            }
            // number is already float number, ignore dot(.)
        } else {
            if ((currentAnswer.length() == 1) && currentAnswer.indexOf("0") == 0) {
                // current user input is 0
                // clear current value(0)
                currentAnswer.setLength(0);
            }
            currentAnswer.append(input);
        }

        editTextAnswer.setText(currentAnswer);
        editTextAnswer.setSelection(editTextAnswer.getText().length());     // move cursor to end
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
        String userAnswer = editTextAnswer.getText().toString();

        // check if quiz is available
        if (textViewQuiz.length() < 1) {
            Toast.makeText(this, "Please generate a quiz first!", Toast.LENGTH_LONG).show();
            return;
        }

        // user input is only minus sign without number
        if (userAnswer.equals("-")) {
            Toast.makeText(this, "Please enter number", Toast.LENGTH_LONG).show();
            return;
        }

        // valid input
        MathQuiz mathQuizAnswer = new MathQuiz(operator, operand1, operand2);

        // set user Answer to currentQuiz
        mathQuizAnswer.setUserAnswer(userAnswer);

        // Add to the mathQuizCollection
        mathQuizCollection.getQuizList().add(mathQuizAnswer);

        // show message to user
        if (mathQuizAnswer.isValidAnswer()) {
            Toast.makeText(this, "Right", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_LONG).show();
        }
    }

    private void clearDisplay() {
        editTextAnswer.setText("");

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

    private void exitProgram() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit program")
                .setMessage("Do you want to exit ?")
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_info)

                .setPositiveButton("Yes", (dialog, i) -> finish())
                .setNegativeButton("No", null);

        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if ((requestCode == REQUEST_SHOW_RESULT) && (data != null)) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                // display register name and score in the title
                textViewTitle.setText(String.format("%s, Score: %d%%", name, mathQuizCollection.getScore()));
            }
        }
    }
}
