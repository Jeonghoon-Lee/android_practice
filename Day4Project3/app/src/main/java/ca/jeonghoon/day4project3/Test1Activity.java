package ca.jeonghoon.day4project3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Test1Activity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    TextView textViewQuestion;
    EditText editAnswer;
    Button btnValidate;
    int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        initialize();
    }

    private void initialize() {
        textViewQuestion = findViewById(R.id.textQuestion);

        editAnswer = findViewById(R.id.editText);
        editAnswer.addTextChangedListener(this);    // validate

        btnValidate = findViewById(R.id.btnValidate);
        btnValidate.setOnClickListener(this);
        btnValidate.setEnabled(false);

        findViewById(R.id.btnGenerate).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGenerate:
                generate();
                break;
            case R.id.btnValidate:
                validate();
                break;
            case R.id.btnCancel:
                cancelActivity();
                break;
        }
    }

    public void generate() {
        Random random = new Random();
        int operand1 = random.nextInt(10);
        int operand2 = random.nextInt(10);
        answer = operand1 + operand2;

        textViewQuestion.setText(String.format("%d + %d = ?", operand1, operand2));
    }

    public void validate() {
        int integerUserAnswer = Integer.valueOf(editAnswer.getText().toString());

        String strResult = (integerUserAnswer == answer) ? "Right Answer!" : "Wrong Answer!";

        Intent intent = new Intent();
        intent.putExtra("return_result_tag", strResult);

        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelActivity() {
        Intent intent = new Intent();
        intent.putExtra("cancel_tag", "Operation canceled");

        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        try {
            int nb = Integer.valueOf(editAnswer.getText().toString());
            if (nb > 18) {
                Toast.makeText(this, "The total should be <= 18", Toast.LENGTH_SHORT).show();
                btnValidate.setEnabled(false);
            } else
                btnValidate.setEnabled(true);
        } catch (Exception e) {
            Toast.makeText(this, "Enter a number data type", Toast.LENGTH_SHORT).show();
        }
    }
}
