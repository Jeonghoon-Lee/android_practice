package ca.jeonghoon.day7component.toast;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ToastBuilder {
    private static final int RED = 0xfff44336;

    public static Toast alert(Context context, String text, int duration) {
        ToastCustom toastCustom = new ToastCustom(context);

        toastCustom.setText(text);
        toastCustom.getToast().setDuration(duration);
        toastCustom.getToast().setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 50);
        toastCustom.setIcon(android.R.drawable.ic_dialog_alert);
        toastCustom.getView().setBackgroundColor(RED);

        return toastCustom.getToast();
    }

    public static Toast imageToast(Context context, int imageRes, int duration) {
        Toast toast = new Toast(context);
        ImageView imageView = new ImageView(context);
        LinearLayout layout = new LinearLayout(context);

        layout.setLayoutParams(new LinearLayout.LayoutParams(850, 650));
        layout.addView(imageView);

        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.requestLayout();
        imageView.setLayoutParams(layout.getLayoutParams());
        imageView.setImageResource(imageRes);

        toast.setView(layout);
        toast.setDuration(duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 500);

        return toast;
    }
}
