package com.example.dreamilyeats.Model;

import java.util.ArrayList;

public class ParentModel {
    String title;
    ArrayList<ChildModel> arrayList ;


    public ParentModel(String title, ArrayList<ChildModel> arrayList) {
        this.title = title;
        this.arrayList = arrayList;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ChildModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<ChildModel> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "ParentModel{" +
                "title='" + title + '\'' +
                ", arrayList=" + arrayList +
                '}';
    }
}
