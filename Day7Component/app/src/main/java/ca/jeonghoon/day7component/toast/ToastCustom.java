package ca.jeonghoon.day7component.toast;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ca.jeonghoon.day7component.R;

public class ToastCustom {

    Toast toast;

    private Context context;
    public View view;

    public ToastCustom(Context context) {
        this.context = context;
        this.toast = new Toast(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(R.layout.colored_toast_layout, null);

        toast.setView(view);
    }

    public Toast getToast() {
        return toast;
    }

    public View getView() {
        return view;
    }

    public void setText(String text) {
        if (view == null) return;

        ((TextView) view.findViewById(R.id.toast_msg)).setText(text);
    }

    public void setIcon(int iconResId) {
        if (view == null) return;

        ((ImageView) view.findViewById(R.id.toast_icon)).setImageResource(iconResId);
    }
}
