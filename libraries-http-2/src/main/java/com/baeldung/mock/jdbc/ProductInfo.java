package com.baeldung.mock.jdbc;

import java.util.UUID;

public class ProductInfo {


    private UUID productId;
    private String name;

    private String description;
    private double price;

    public ProductInfo(UUID id, String descr, String name, double price){
        this.productId = id;
        this.description = descr;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

}
