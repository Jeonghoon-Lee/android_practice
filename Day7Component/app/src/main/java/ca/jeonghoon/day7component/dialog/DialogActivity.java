package ca.jeonghoon.day7component.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import ca.jeonghoon.day7component.R;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initialize();
    }

    private void initialize() {
        findViewById(R.id.btnAlert1).setOnClickListener(this);
        findViewById(R.id.btnAlert2).setOnClickListener(this);
        findViewById(R.id.btnAlert3).setOnClickListener(this);
        findViewById(R.id.btnAlert4).setOnClickListener(this);
        findViewById(R.id.btnProgressDialog).setOnClickListener(this);
        findViewById(R.id.btnCustomDialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAlert1:
                showAlertDialog1();
                break;
            case R.id.btnAlert2:
                showAlertDialog2();
                break;
            case R.id.btnAlert3:
                showAlertDialog3();
                break;
            case R.id.btnAlert4:
                showAlertDialog4();
                break;
            case R.id.btnProgressDialog:
                showProgressDialog();
                break;
            case R.id.btnCustomDialog:
                customDialog();
                break;
        }
    }

    private void showAlertDialog1() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("This is my title");

        // second argument in below methods is a reference to event listener
        alertDialogBuilder.setPositiveButton("Yes", this);
        alertDialogBuilder.setNegativeButton("No", this);
        alertDialogBuilder.setNeutralButton("Neutral", this);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void showAlertDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("AlertDialog\nJust a question for demo")
                .setMessage("Do you want to delete this file?")
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_info)

                .setPositiveButton("Yes", (dialog, i) ->
                    Toast.makeText(DialogActivity.this, "id = " + i, Toast.LENGTH_LONG).show()
                )
                .setNegativeButton("No", null)
                .setNeutralButton("Neutral", null);

        builder.show();
    }

    private void showAlertDialog3() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Question ?")
                .setCancelable(false)
                .setSingleChoiceItems(new String[]{"A", "B", "C", "D"},
                        0,
                        (dialog, item) -> Toast.makeText(DialogActivity.this, "id = " + item, Toast.LENGTH_LONG).show())
                .setPositiveButton("Yes", null);

        builder.show();
    }

    private void showAlertDialog4() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("")
                .setCancelable(true)
                .setMultiChoiceItems(new String[]{"Item0", "Item1", "Item2", "Item3", "Item4", "Item5"},
                        new boolean[] {false, true, false, true, false, false},
                        (dialog, item, value) -> Toast.makeText(DialogActivity.this, "Item" + item + " : " + value, Toast.LENGTH_LONG).show())
                .setPositiveButton("Yes", null);

        builder.show();
    }

    private void showProgressDialog() {
        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("progress dialog example");
        progressDialog.setMessage("please wait ...");

        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        // handler
        progressDialog.setProgress(0);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (progressDialog.getProgress() < progressDialog.getMax()) {
                    progressDialog.incrementProgressBy(1);
                } else {
                    progressDialog.dismiss();
                    this.cancel();
                }
            }
        }, 0, 200);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (progressDialog.getProgress() < progressDialog.getMax()) {
                    progressDialog.incrementProgressBy(1);
                }
            }
        }, 0, 140);
    }

    public void customDialog() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.activity_dialog);
        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                Toast.makeText(this, "id = " + which, Toast.LENGTH_LONG).show();
            case DialogInterface.BUTTON_NEGATIVE:
                Toast.makeText(DialogActivity.this, "id = " + which, Toast.LENGTH_LONG).show();
            case DialogInterface.BUTTON_NEUTRAL:
                Toast.makeText(DialogActivity.this, "id = " + which, Toast.LENGTH_LONG).show();
        }
    }}
