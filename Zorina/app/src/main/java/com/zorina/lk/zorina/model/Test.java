package com.zorina.lk.zorina.model;

public class Test {

    private String price;
    private  String title;

    private  String size;

    private  String qty;
    private  String productId;

    public Test(String price, String title, String size, String productId,String qty) {
        this.price = price;
        this.title = title;
        this.size = size;
        this.productId = productId;
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(int String) {
        this.qty = qty;
    }
}
