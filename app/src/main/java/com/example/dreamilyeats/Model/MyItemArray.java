package com.example.dreamilyeats.Model;

public class MyItemArray {

    Double item_price;
    int number_of_items;
    String item_name;


    public MyItemArray(Double item_price, int number_of_items, String item_name) {
        this.item_price = item_price;
        this.number_of_items = number_of_items;
        this.item_name = item_name;
    }



    public Double getItem_price() {
        return item_price;
    }

    public void setItem_price(Double item_price) {
        this.item_price = item_price;
    }

    public int getNumber_of_items() {
        return number_of_items;
    }

    public void setNumber_of_items(int number_of_items) {
        this.number_of_items = number_of_items;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }




    @Override
    public String toString() {
        return "MyItemArray{" +
                "item_price=" + item_price +
                ", number_of_items=" + number_of_items +
                ", item_name='" + item_name + '\'' +
                '}';
    }
}
