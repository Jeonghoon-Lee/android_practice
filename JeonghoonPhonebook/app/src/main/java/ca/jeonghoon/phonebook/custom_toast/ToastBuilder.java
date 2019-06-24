package ca.jeonghoon.phonebook.custom_toast;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastBuilder {
    // display toast message with image
    public static Toast displayImageToast(Context context, String text, int imageRes, int duration) {
        ImageToastCustom toastCustom = new ImageToastCustom(context);

        toastCustom.setText(text);
        toastCustom.getToast().setDuration(duration);
        toastCustom.getToast().setGravity(Gravity.CENTER, 0, 0);
        toastCustom.setPhoto(imageRes);

        return toastCustom.getToast();
    }
}
