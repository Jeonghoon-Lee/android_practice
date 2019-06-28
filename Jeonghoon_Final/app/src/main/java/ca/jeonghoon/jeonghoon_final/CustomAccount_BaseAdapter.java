package ca.jeonghoon.jeonghoon_final;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ca.jeonghoon.jeonghoon_final.model.Account;

public class CustomAccount_BaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Account> accountList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CustomAccount_BaseAdapter(Context context, ArrayList<Account> accountList) {
        this.context = context;

        // sort
        accountList.sort((a1, a2) -> a1.getAccountHolder().getLastName()
                .compareTo(a2.getAccountHolder().getLastName()));

        this.accountList = accountList;
    }

    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public Object getItem(int position) {
        return accountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position,
                        View cellReusableViewObject,
                        ViewGroup parent) {

        Account account = accountList.get(position);

        // If cellReusableViewObject is already created
        // do not make it again, just reuse it and fill it with new information
        if (cellReusableViewObject == null) {
            cellReusableViewObject = LayoutInflater.from(context).inflate(R.layout.custom_list_item,
                                                                          parent,
                                                                         false);
        }

        // Create reference to cell elements -------------------------------------------------------
        TextView cell_firstName = cellReusableViewObject.findViewById(R.id.textViewFirstName);
        TextView cell_lastName = cellReusableViewObject.findViewById(R.id.textViewLastName);
        TextView cell_account = cellReusableViewObject.findViewById(R.id.textViewAccount);
        //------------------------------------------------------------------------------------------

        // Set value for cell elements -------------------------------------------------------------
        cell_firstName.setText(account.getAccountHolder().getFirstName());
        cell_lastName.setText(account.getAccountHolder().getLastName());
        cell_account.setText(account.getAccountNumber());

        return cellReusableViewObject;
    }
}