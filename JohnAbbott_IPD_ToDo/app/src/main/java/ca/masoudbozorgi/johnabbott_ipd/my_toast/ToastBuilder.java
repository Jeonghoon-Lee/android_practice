package ca.masoudbozorgi.johnabbott_ipd.my_toast;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import ca.masoudbozorgi.johnabbott_ipd.R;

public class ToastBuilder {

    private static final int RED = 0xfff44336;
    private static final int BLUE = 0xff2195f3;
    private static final int ORANGE = 0xffffc107;
    private static final int GREEN = 0xff4caf50;

    private static final int IC_ALERT = R.drawable.ic_alert;
    private static final int IC_WARNING = R.drawable.ic_warning;
    private static final int IC_INFO = R.drawable.ic_info;
    private static final int IC_SUCCESS = R.drawable.ic_success;

    public static final int LENGTH_LONG  = 1;
    public static final int LENGTH_SHORT = 0;



    public static Toast alert(Context context, String text, int duration){

        ToastCustom toastCustom = new ToastCustom(context);
        toastCustom.setText(text);
        toastCustom.getToast().setDuration(duration);
        toastCustom.getToast().setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 50);
        toastCustom.setIcon(IC_ALERT);
        toastCustom.getView().setBackgroundColor(RED);
        return toastCustom.getToast();
    }


    public static Toast info(Context context, String text, int duration){

        ToastCustom toastCustom = new ToastCustom(context);
        toastCustom.setText(text);
        toastCustom.getToast().setDuration(duration);
        toastCustom.getToast().setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 50);
        toastCustom.setIcon(IC_INFO);
        toastCustom.getView().setBackgroundColor(BLUE);
        return toastCustom.getToast();
    }


    public static Toast warning(Context context, String text, int duration){

        ToastCustom toastCustom = new ToastCustom(context);
        toastCustom.setText(text);
        toastCustom.getToast().setDuration(duration);
        toastCustom.getToast().setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 50);
        toastCustom.setIcon(IC_WARNING);
        toastCustom.getView().setBackgroundColor(ORANGE);
        return toastCustom.getToast();
    }


    public static Toast success(Context context, String text, int duration){

        ToastCustom toastCustom = new ToastCustom(context);
        toastCustom.setText(text);
        toastCustom.getToast().setDuration(duration);
        toastCustom.getToast().setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 50);
        toastCustom.setIcon(IC_SUCCESS);
        toastCustom.getView().setBackgroundColor(GREEN);
        return toastCustom.getToast();
    }


    public static Toast imageToast(Context context, int imageRes, int duration){

        Toast toast = new Toast(context);

        // Programmatic Layout .....................................................................
        // 1- Create a Layout
        LinearLayout layout = new LinearLayout(context);

        // 2- Set the root element and its size
        layout.setLayoutParams(new LinearLayout.LayoutParams(850, 650));

        // 3- Create a widget
        ImageView iv = new ImageView(context);

        // 4- Customize widget
        iv.setLayoutParams(layout.getLayoutParams());
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);

        // If something about your view changes that will affect the size,
        // then you should call requestLayout().
        // 5- Apply changes to Layout
        iv.requestLayout();

        // 6- Set image for ImageView
        iv.setImageResource(imageRes);

        // 7- Add defined widget to root element
        layout.addView(iv);
        //..........................................................................................

        toast.setView(layout);
        toast.setDuration(duration);
        //toast.setGravity(Gravity.CENTER, 0, 600);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 500);
        return toast;
    }
}
