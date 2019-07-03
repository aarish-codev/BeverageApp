package com.coditas.app.model;

public enum ItemEnum {
    COFFEE("coffee", 5.0d), CHAI("chai", 4.0d), BANANA_SMOOTHIE("banana smoothie", 6.0d), STRAWBERRY_SHAKE("strawberry shake", 7.0d), MOJITO("mojito", 7.5d);

    private String displayName;
    private double price;

    ItemEnum(String displayName, double price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
