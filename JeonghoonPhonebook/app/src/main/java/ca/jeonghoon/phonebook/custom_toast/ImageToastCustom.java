package ca.jeonghoon.phonebook.custom_toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ca.jeonghoon.phonebook.R;

public class ImageToastCustom {

    Toast toast;

    private Context context;
    public View view;

    public ImageToastCustom(Context context) {
        this.context = context;
        this.toast = new Toast(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(R.layout.custom_toast_layout, null);

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

    public void setPhoto(int imageResId) {
        if (view == null) return;

        ((ImageView) view.findViewById(R.id.toast_photo)).setImageResource(imageResId);
    }
}
