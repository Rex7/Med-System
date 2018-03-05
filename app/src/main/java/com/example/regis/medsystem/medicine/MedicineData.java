package com.example.regis.medsystem.medicine;


public class MedicineData {
    String drugName, category, usage, sideEffects;
    int price, drugId;

    public MedicineData(String drugName, String category, String usage, String sideEffects, int price, int drugId) {
        this.drugName = drugName;
        this.category = category;
        this.usage = usage;
        this.sideEffects = sideEffects;
        this.price = price;
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }
}
