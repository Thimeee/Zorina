package com.zorina.lk.zorina.model;

public class Material {

    private int id;
    private String MaterialName ;

    public Material(int id, String MaterialName) {
        this.id = id;
        this.MaterialName = MaterialName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        this.MaterialName = materialName;
    }
}
