package ca.masoudbozorgi.johnabbott_ipd.my_toast;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ca.masoudbozorgi.johnabbott_ipd.R;

public class ToastCustom {

    Toast toast;

    // view and context are the elements we need to customize the toast object
    private Context context;
    public View view;

    public ToastCustom(Context context){

        // 1- Create a Toast object in defined context
        this.context = context;
        this.toast = new Toast(context);

        // 2- Crete a View Object from XML Layout and assign it to view reference in ToastCustom
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(R.layout.colored_toast_layout, null);

        // 3- Set the created view for Toast object
        toast.setView(view);
    }

    public Toast getToast(){
        return toast;
    }

    public View getView(){
        return view;
    }

    public void setText(String text){
        if(view==null)
            return;
        ((TextView) view.findViewById(R.id.toast_msg)).setText(text);
    }

    public void setIcon(int iconResId){
        if(view==null)
            return;
        ((ImageView) view.findViewById(R.id.toast_icon)).setImageResource(iconResId);
    }
}
