package com.supermarket.CheckOutJavaMaven;

import java.util.Map;

public class PricingRules {
    public int calculateTotalPrice(Map<String, Integer> shoppingCart, Map<String,Items> itemsMap) {
        Items items = new Items();
        int totalAmount = 0;
        if (shoppingCart.size() > 0 && itemsMap.size() > 0) {
            for (Map.Entry<String,Integer> entry : shoppingCart.entrySet()) {
                int multiple = 0;
                int remainUnit = 0;
                int subTotal = 0;
                Items item = itemsMap.get(entry.getKey());
                // Special Price
                if (item.getDiscount().equals("Y")) {
                    // Times of Special Price
                    multiple = entry.getValue() / item.getSpecialUnit();
                    // Remaining of Original Price
                    remainUnit = entry.getValue() % item.getSpecialUnit();

                    subTotal = subTotal + item.getSpecialPrice() * multiple + item.getPrice() * remainUnit;
                    // Original Price
                } else
                    subTotal = subTotal + item.getPrice() * entry.getValue();
                System.out.println("Item: "+item.getSku()+", unit scanned: "+entry.getValue()+", amount: "+subTotal);

                // Total amount
                totalAmount = totalAmount + subTotal;
            }
        }
        return totalAmount;
    }
}
