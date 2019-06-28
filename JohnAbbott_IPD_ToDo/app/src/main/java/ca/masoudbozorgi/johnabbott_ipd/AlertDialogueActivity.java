package ca.masoudbozorgi.johnabbott_ipd;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class AlertDialogueActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialogue);
    }


    //AlertDialog.Builder onClick ------------------------------------------------------------------
    public void showAlertDialog1(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("This is my title");

        // Second argument in below methods is a reference to event listener
        alertDialogBuilder.setPositiveButton("Yes", this);
        alertDialogBuilder.setNegativeButton("No", this);
        alertDialogBuilder.setNeutralButton("Neutral", this);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                Toast.makeText(AlertDialogueActivity.this, "id = " + which, Toast.LENGTH_SHORT).show();
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                Toast.makeText(AlertDialogueActivity.this, "id = " + which, Toast.LENGTH_SHORT).show();
                break;

            case DialogInterface.BUTTON_NEUTRAL:
                Toast.makeText(AlertDialogueActivity.this, "id = " + which, Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //----------------------------------------------------------------------------------------------


    public void showAlertDialog2(View view) {

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
                        Toast.makeText(AlertDialogueActivity.this, "File Deleted!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .setNeutralButton("Cancel", null);

        builder.show();
    }

    // Radio Group - Single Choice
    public void showAlertDialog3(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // type 3
        builder.setTitle("Question ?")
                .setCancelable(false)
                .setSingleChoiceItems(new String[]{"A", "B", "C", "D"}, // Single Choice
                        0,                                   // -1 for no default selection
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AlertDialogueActivity.this, "i = " + i, Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("OK", null);

        builder.show();
    }

    // Check box - Multi Choice
    public void showAlertDialog4(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // type 4
        builder.setTitle("")
                .setCancelable(true)
                .setMultiChoiceItems(new String[]{"item0", "item1", "item2", "item3", "item4", "item5"}, // Multi Choice
                        new boolean[]{false, true, false, true, true, false},                            // Define default state
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {     // i for index, b for boolean state
                                Toast.makeText(AlertDialogueActivity.this,
                                        "item" + i + " : " + b,
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("Ok", null);

        builder.show();
    }


    // This method is called by onClick
    public void showProgressDialog(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);

        // Disable ability to dismiss progress dialog
        //progressDialog.setCancelable(false);
        progressDialog.setTitle("progress dialog example");
        progressDialog.setMessage("please wait ...");

        // By removing this line the default style will be used which is Spinner
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        // We can use this line when we do not know when the progress will be finished
        // For activating this line simulator code must be disabled
//        progressDialog.setIndeterminate(true);
        progressDialog.show();




        // Create a simulator to start the progress from 0 and increase to 100
        progressDialog.setProgress(0);

        // Change the progress in the specific time interval
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                // progressDialog.getMax() is 100 %
                if (progressDialog.getProgress() < progressDialog.getMax()) {
                    // Increase progress 1 % in each 200 ms
                    progressDialog.incrementProgressBy(1);
                } else {
                    // progressDialog.getMax() == 100 % dismiss progressDialog
                    progressDialog.dismiss();
                    this.cancel();
                }
            }
        }, 0, 200);


        // Secondary progress can be used to show caching data
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (progressDialog.getSecondaryProgress() < progressDialog.getMax()) {
                    progressDialog.incrementSecondaryProgressBy(1);
                }
            }
        }, 0, 140);
    }


    public void customDialog(View view){
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.activity_alert_dialogue);
        dialog.show();
    }
}
