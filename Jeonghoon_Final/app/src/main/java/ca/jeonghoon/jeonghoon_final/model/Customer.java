package ca.jeonghoon.jeonghoon_final.model;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {
    private String firstName;
    private String lastName;
    private String phone;
    private String sin;

    public Customer(String firstName, String lastName, String phone, String sin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.sin = sin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(sin, customer.sin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phone, sin);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", sin='" + sin + '\'' +
                '}';
    }
}
