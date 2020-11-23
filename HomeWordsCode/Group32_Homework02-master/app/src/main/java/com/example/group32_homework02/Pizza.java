package com.example.group32_homework02;

import java.io.Serializable;
import java.util.ArrayList;

public class Pizza  implements Serializable {
    String add_Topping;
    String clear_Pizza;
    String delivery;
    ArrayList<String> toppings = new ArrayList<>();
    boolean deliveryBool;

    public Pizza() {
    }

    public Pizza(String add_Topping, String clear_Pizza, String delivery) {
        this.add_Topping = add_Topping;
        this.clear_Pizza = clear_Pizza;
        this.delivery = delivery;
    }

    public String getAdd_Topping() {
        return add_Topping;
    }

    public String getClear_Pizza() {
        return clear_Pizza;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setAdd_Topping(String add_Topping) {
        this.add_Topping = add_Topping;
    }

    public void setClear_Pizza(String clear_Pizza) {
        this.clear_Pizza = clear_Pizza;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "add_Topping='" + add_Topping + '\'' +
                ", clear_Pizza='" + clear_Pizza + '\'' +
                ", delivery='" + delivery + '\'' +
                '}';
    }
}
