package ca.jeonghoon.jeonghoon_final.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AccountCollection implements Serializable {
    private static ArrayList<Account> accountList = new ArrayList<>();

    public AccountCollection() {
    }

    public static ArrayList<Account> getAccountList() {
        return accountList;
    }
}
