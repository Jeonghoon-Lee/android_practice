package ca.jeonghoon.phonebook.model;

import java.util.ArrayList;
import java.util.List;

public class PersonCollection {
    private static List<Person> personList = new ArrayList<>();

    public static List<Person> getPersonList() {
        return personList;
    }
}
