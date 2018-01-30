package com.example.regis.medsystem;


public class Drug {
    private String drugName;

    public Drug(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
}
