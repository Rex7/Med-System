package com.example.regis.medsystem.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "medicine")
public class Medicine {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int medId;
    @ColumnInfo(name = "medicine_name")
    private String medName;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "price")
    private int price;

    public Medicine(int medId, String medName, String category, int price) {
        this.medId = medId;
        this.medName = medName;
        this.category = category;
        this.price = price;
    }

    @Ignore
    public Medicine() {

    }
    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }
}
