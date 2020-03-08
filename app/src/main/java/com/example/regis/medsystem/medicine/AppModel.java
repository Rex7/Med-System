package com.example.regis.medsystem.medicine;

public class AppModel {
    private String name,size;


    public AppModel(String name,String size) {
        this.name = name;
        this.size=size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
