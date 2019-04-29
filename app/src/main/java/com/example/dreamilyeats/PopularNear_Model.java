package com.example.dreamilyeats;

class PopularNear_Model {
    int offer_images;
    String hotel_name;
    String price_for;
    String time;
    String grade;

    public PopularNear_Model(int offer_images, String hotel_name, String price_for, String time, String grade) {
        this.offer_images = offer_images;
        this.hotel_name = hotel_name;
        this.price_for = price_for;
        this.time = time;
        this.grade = grade;
    }


    public int getOffer_images() {
        return offer_images;
    }

    public void setOffer_images(int offer_images) {
        this.offer_images = offer_images;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getPrice_for() {
        return price_for;
    }

    public void setPrice_for(String price_for) {
        this.price_for = price_for;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
