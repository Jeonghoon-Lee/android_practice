package ca.jeonghoon.day5passingindent.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientCollection implements Serializable {
    private ArrayList<Client> clientList = new ArrayList<>();

    public ArrayList<Client> getClientList() {
        return clientList;
    }
}
