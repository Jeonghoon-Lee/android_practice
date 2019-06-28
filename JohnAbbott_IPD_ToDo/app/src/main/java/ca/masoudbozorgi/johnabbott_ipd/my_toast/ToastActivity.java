package ca.masoudbozorgi.johnabbott_ipd.my_toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ca.masoudbozorgi.johnabbott_ipd.R;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
    }

    public void simpleToast(View v) {
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
    }


    public void colorToast(View v){

        // An example of customizing a Toast
        Toast toast = Toast.makeText(this, "Test Color For Toast", Toast.LENGTH_SHORT);
        toast.getView().setBackgroundColor(0x7ff44436);
        toast.show();
    }


    public void btnOnClick(View v){

        // An example of using tags
        String tag = (String) v.getTag();

        switch (tag){
            case "alert" :
                ToastBuilder.alert(this, "this is an alert toast", 0).show();
                break;

            case "warning" :
                ToastBuilder.warning(this, "this is a warning toast", ToastBuilder.LENGTH_SHORT).show();
                break;

            case "info" :
                ToastBuilder.info(this, "this is an info toast", ToastBuilder.LENGTH_SHORT).show();
                break;

            case "success":
                ToastBuilder.success(this, "this is a success toast", ToastBuilder.LENGTH_SHORT).show();
                break;

            case "image":
                ToastBuilder.imageToast(this, R.drawable.double_d, ToastBuilder.LENGTH_SHORT).show();
                break;

            default: break;
        }
    }
}