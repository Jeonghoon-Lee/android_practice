package ca.jeonghoon.day5passingindent.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataCollection implements Serializable {
    private List<Client> clientList = new ArrayList<Client>();

    public List<Client> getClientList() {
        return clientList;
    }
}
