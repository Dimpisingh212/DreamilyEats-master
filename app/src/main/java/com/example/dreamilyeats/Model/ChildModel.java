package com.example.dreamilyeats.Model;

import java.io.Serializable;

public class ChildModel implements Serializable {
    String dish_name;
    String about;
    String price;
    String vegetarian;
    int veg_icon;

    public ChildModel(String dish_name, String about, String price, String vegetarian, int veg_icon) {
        this.dish_name = dish_name;
        this.about = about;
        this.price = price;
        this.vegetarian = vegetarian;
        this.veg_icon = veg_icon;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(String vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getVeg_icon() {
        return veg_icon;
    }

    public void setVeg_icon(int veg_icon) {
        this.veg_icon = veg_icon;
    }


    @Override
    public String toString() {
        return "ChildModel{" +
                "dish_name='" + dish_name + '\'' +
                ", about='" + about + '\'' +
                ", price='" + price + '\'' +
                ", vegetarian='" + vegetarian + '\'' +
                ", veg_icon=" + veg_icon +
                '}';
    }
}
