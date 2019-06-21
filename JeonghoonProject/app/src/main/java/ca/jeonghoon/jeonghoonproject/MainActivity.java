package ca.jeonghoon.jeonghoonproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import ca.jeonghoon.jeonghoonproject.Model.MathQuiz;
import ca.jeonghoon.jeonghoonproject.Model.MathQuizCollection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextAnswer;
    TextView textViewQuiz;

    MathQuiz currentQuiz;
    MathQuizCollection mathQuizCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        editTextAnswer = findViewById(R.id.editTextAnswer);
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
                break;
            case R.id.buttonClear:
                break;

            case R.id.buttonScore:
                score();
                break;
            case R.id.buttonFinish:
                finish();
                break;
        }
    }

    private void processUserInput(View view) {
        String answer = editTextAnswer.getText().toString();

        switch (view.getId()) {
            case R.id.button1:
                answer += "1";
                break;
            case R.id.button2:
                answer += "2";
                break;
            case R.id.button3:
                answer += "3";
                break;
            case R.id.button4:
                answer += "4";
                break;
            case R.id.button5:
                answer += "5";
                break;
            case R.id.button6:
                answer += "6";
                break;
            case R.id.button7:
                answer += "7";
                break;
            case R.id.button8:
                answer += "8";
                break;
            case R.id.button9:
                answer += "9";
                break;
            case R.id.button0:
                answer += "0";
                break;
            case R.id.buttonDot:
                answer += ".";
                break;
            case R.id.buttonMinus:
                answer += "-";
                break;
        }
        editTextAnswer.setText(answer);
    }

    private void generateQuiz() {
        final int ADD = 0;
        final int SUBTRACT = 1;
        final int MULTIPLY = 2;
        final int DIVIDE = 3;

        Random rand = new Random();
        int operator = rand.nextInt(4);
        int operand1 = rand.nextInt(21) - 10;   // generator -10 ~ 10
        int operand2 = rand.nextInt(21) - 10;   // generator -10 ~ 10

        String quiz = "";
        switch (operator) {
            case ADD:
                quiz = String.format("%d + %d", operand1, operand2);
                break;
            case SUBTRACT:
                quiz = String.format("%d - %d", operand1, operand2);
                break;
            case MULTIPLY:
                quiz = String.format("%d * %d", operand1, operand2);
                break;
            case DIVIDE:
                if (operand2 == 0)      // prevent error (divide by 0)
                    operand2++;
                quiz = String.format("%d / %d", operand1, operand2);
                break;
        }
        textViewQuiz.setText(quiz);
    }

    private void score() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}
