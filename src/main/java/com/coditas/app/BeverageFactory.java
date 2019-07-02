package com.coditas.app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BeverageFactory {

    //  This stores the combination of Items with allowed exclusions
    Map<String, List<String>> beveragesMap = DataLoader.getBeveragesMap();
    //  This stores the price of Items
    Map<String, Double> itemRates = DataLoader.getItemRates();
    //  This stores the price of exclusions
    Map<String, Double> ingredientRates = DataLoader.getIngredientRates();


    public double getInvoiceFromOrder(String order) {
        Double cost = 0.0d;
        List<String> orderItems = getItemsFromOrder(order.trim());
        for (String item : orderItems) {
            List<String> itemWithIngredients = getIngredientFromItem(item);
            boolean isValidOrder = checkIfValidOrder(item);
            if (isValidOrder) {
                cost = cost + calculateInvoice(itemWithIngredients);
            } else {
                throw new InvalidOrderException("Invalid Order..!! You cannot exclude all items from " + item);
            }
        }
        return cost;

    }

    public List<String> getItemsFromOrder(String order1) {
        // this returns a List which has order with exclusions at each index, all in Lower cases
        // Chai, -milk, -water, Mojito, Coffee, -milk, -sugar will have
        //        Chai, -milk, -water
        //        Mojito
        //        Coffee, -milk, -sugar
        return Arrays.stream(order1.split("(?!, -),")).map(String::trim).map(String::toLowerCase).collect(Collectors.toList());
    }

    public List<String> getIngredientFromItem(String item) {
        // Similar to getItemsFromOrder(), but this replaces '-' from the order
        return Arrays.stream(item.split(",")).map(s -> s.replace("-", "")).map(String::trim).collect(Collectors.toList());
    }

    public boolean checkIfValidOrder(String item) {
        List<String> itemIngredients = getIngredientFromItem(item);
        // case: When typo in order
        if (item.length() < 1 || (!beveragesMap.containsKey(itemIngredients.get(0))))
            throw new BeverageTypoException("Typo in order, try again");
            // order has no typo, now check if all ingredients are not excluded in an order
        else {
            List<String> allIngredients = beveragesMap.get(itemIngredients.get(0)); // Beverage name is always at index 0
            if (itemIngredients.size() == allIngredients.size() + 1)
                return false;
        }
        return true;
    }

    public Double calculateInvoice(List<String> itemWithIngredients) {
        Double itemValue = itemRates.get(itemWithIngredients.get(0));
        Double ingredientsValue = 0.0d;
        int size = itemWithIngredients.size();
        if (size > 1)
            for (int i = 1; i < size; i++) {
                ingredientsValue = ingredientsValue + ingredientRates.get(itemWithIngredients.get(i));
            }
        return itemValue - ingredientsValue;
    }

}
