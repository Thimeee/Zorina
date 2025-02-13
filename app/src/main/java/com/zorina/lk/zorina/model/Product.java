package com.zorina.lk.zorina.model;

public class Product {

    private int id;

    private String productName;


    public  Product(){}

    public Product(int id, String productName, String productCode, double productPrice, int productQty, int productStatus, Material material) {
        this.id = id;
        this.productName = productName;
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.productStatus = productStatus;
        this.material = material;
    }

    private String productCode;

    private double productPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    private int productQty;

    private int productStatus;
    private Material material;
}
