package ca.jeonghoon.jeonghoon_mid.Model;

import java.io.Serializable;

public class Order implements Serializable {
    private String userName;
    private String order;
    private String userAdderss;

    public Order() {}

    public Order(String userName, String order) {
        this.userName = userName;
        this.order = order;
    }

    public Order(String userName, String order, String userAdderss) {
        this.userName = userName;
        this.order = order;
        this.userAdderss = userAdderss;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getUserAdderss() {
        return userAdderss;
    }

    public void setUserAdderss(String userAdderss) {
        this.userAdderss = userAdderss;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userName='" + userName + '\'' +
                ", order='" + order + '\'' +
                ", userAdderss='" + userAdderss + '\'' +
                '}';
    }
}
