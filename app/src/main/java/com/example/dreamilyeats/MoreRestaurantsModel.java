package com.example.dreamilyeats;

class MoreRestaurantsModel {
    int food_image;
    String restaurant_name;
    String dishes_type;
    String grade;


    public MoreRestaurantsModel(int food_image, String restaurant_name, String dishes_type, String grade) {
        this.food_image = food_image;
        this.restaurant_name = restaurant_name;
        this.dishes_type = dishes_type;
        this.grade = grade;
    }

    public int getFood_image() {
        return food_image;
    }

    public void setFood_image(int food_image) {
        this.food_image = food_image;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getDishes_type() {
        return dishes_type;
    }

    public void setDishes_type(String dishes_type) {
        this.dishes_type = dishes_type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "MoreRestaurantsModel{" +
                "food_image=" + food_image +
                ", restaurant_name='" + restaurant_name + '\'' +
                ", dishes_type='" + dishes_type + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
