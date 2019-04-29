package com.example.dreamilyeats.Model;

import java.util.Iterator;

public class PlaceOrderListModel {


    int hotel_image;
    String hotel_name;
    String time_date;
    String total_cost;


    public PlaceOrderListModel(int hotel_image, String hotel_name, String time_date,String total_cost) {
        this.hotel_image = hotel_image;
        this.hotel_name = hotel_name;
        this.time_date = time_date;
        this.total_cost = total_cost;
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


    public String getTime_date() {
        return time_date;
    }

    public void setTime_date(String time_date) {
        this.time_date = time_date;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }


    @Override
    public String toString() {
        return "PlaceOrderListModel{" +
                "hotel_image=" + hotel_image +
                ", hotel_name='" + hotel_name + '\'' +
                ", time_date='" + time_date + '\'' +
                ", total_cost='" + total_cost + '\'' +
                '}';
    }
}
