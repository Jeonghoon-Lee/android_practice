package ca.masoudbozorgi.customlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by masoudbozorgi on 2018-03-07.
 */

public class CellController_BaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Flower> flowerArrayList;

    public CellController_BaseAdapter(Context context, ArrayList<Flower> flowerArrayList) {
        this.context = context;
        this.flowerArrayList = flowerArrayList;
    }

    @Override
    public int getCount() {
        return flowerArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return flowerArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position,
                        View cellReusableViewObject,
                        ViewGroup parent) {

        Flower flower = flowerArrayList.get(position);

        // If cellReusableViewObject is already created
        // do not make it again, just reuse it and fill it with new information
        if (cellReusableViewObject == null) {
            cellReusableViewObject = LayoutInflater.from(context).inflate(R.layout.cell_custom,
                                                                          parent,
                                                                         false);
        }

        // Create reference to cell elements -------------------------------------------------------
        TextView   cell_title        = cellReusableViewObject.findViewById(R.id.cell_title);
        TextView   cell_price        = cellReusableViewObject.findViewById(R.id.cell_price);
        TextView   cell_description  = cellReusableViewObject.findViewById(R.id.cell_description);

        ImageView cell_imageView = cellReusableViewObject.findViewById(R.id.cell_imageView);
        //------------------------------------------------------------------------------------------

        // Set value for cell elements -------------------------------------------------------------
        cell_title.setText(flower.getName());
        cell_price.setText(String.valueOf(flower.getPrice() + " $"));
        cell_description.setText(flower.getInstructions());

        // load image for cell ................................................

        String photoName = flower.getPhoto();

        if (photoName.contains(".")) {

            // remove file extension from file name
            photoName = photoName.substring(0, photoName.lastIndexOf('.'));
        }

        int imageResId = context.getResources().getIdentifier(photoName,
                "drawable",
                context.getApplicationContext().getPackageName());

        cell_imageView.setImageResource(imageResId);
        //.....................................................................

        //------------------------------------------------------------------------------------------

        return cellReusableViewObject;
    }
}