package com.example.dreamilyeats.Model;

public class FavouriteListArray {

    int hotel_image;
    String hotel_name;


    public FavouriteListArray(int hotel_image, String hotel_name) {
        this.hotel_image = hotel_image;
        this.hotel_name = hotel_name;
    }


    public int getHotel_image() {
        return hotel_image;
    }

    public void setHotel_image(int hotel_image) {
        this.hotel_image = hotel_image;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }




    @Override
    public String toString() {
        return "FavouriteListArray{" +
                "hotel_image=" + hotel_image +
                ", hotel_name='" + hotel_name + '\'' +
                '}';
    }
}
