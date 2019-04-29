package com.example.dreamilyeats.Model;

public class ExploreFragmentModel {

    int food_image;
    String food_type_name;

    public ExploreFragmentModel(int food_image, String food_type_name) {
        this.food_image = food_image;
        this.food_type_name = food_type_name;
    }




    public int getFood_image() {
        return food_image;
    }

    public void setFood_image(int food_image) {
        this.food_image = food_image;
    }

    public String getFood_type_name() {
        return food_type_name;
    }

    public void setFood_type_name(String food_type_name) {
        this.food_type_name = food_type_name;
    }
}
