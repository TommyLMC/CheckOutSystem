package com.supermarket.CheckOutJavaMaven;

public class Items {
    private String sku;
    private int price;
    private String discount;
    private int specialUnit;
    private int specialPrice;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getSpecialUnit() {
        return specialUnit;
    }

    public void setSpecialUnit(int specialUnit) {
        this.specialUnit = specialUnit;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }

    @Override
    public String toString() {
        return "Items{" +
                "sku='" + sku + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", specialUnit=" + specialUnit +
                ", specialPrice=" + specialPrice +
                '}';
    }

}
