package com.coditas.app.factory;

import com.coditas.app.exception.BeverageTypoException;
import com.coditas.app.bootstrap.DataLoader;
import com.coditas.app.exception.IllegalIngredientsException;
import com.coditas.app.exception.InvalidOrderException;

import java.util.*;
import java.util.stream.Collectors;

public class BeverageFactory {

    //      This stores the combination of Items with allowed exclusions
    Map<String, List<String>> beveragesMap = DataLoader.getBeveragesMap();
    //      This stores the price of Items
    Map<String, Double> itemRates = DataLoader.getItemRates();
    //      This stores the price of exclusions
    Map<String, Double> ingredientRates = DataLoader.getIngredientRates();

    //      get cost of each item in order and total them
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

    //     this returns a List which has order with exclusions at each index, all in Lower cases
//     Chai, -milk, -water, Mojito, Coffee, -milk, -sugar will have
//     Chai, -milk, -water
//     Mojito
//     Coffee, -milk, -sugar
    public List<String> getItemsFromOrder(String order1) {
        return Arrays.stream(order1.split("(?!,\\s*-),")).map(String::trim).map(String::toLowerCase).collect(Collectors.toList());
    }

    //     Similar to getItemsFromOrder(), but this replaces '-' from the order
    public List<String> getIngredientFromItem(String item) {
        return Arrays.stream(item.split(",")).map(s -> s.replace("-", "")).map(String::trim).collect(Collectors.toList());
    }

    //     if ordered item not present in DataLoader or empty order returns false
    public boolean checkIfValidOrder(String item) {
        List<String> itemIngredients = getIngredientFromItem(item);
        // case: When typo in order
        if (item.length() < 1 || (!beveragesMap.containsKey(itemIngredients.get(0))))
            throw new BeverageTypoException("Looks like there is a Typo in order -> " + item + " \nPlease try again");
            // order has no typo, now check if all ingredients are not excluded in an order
        else {
            // logic is to compare the order items and (total exclusions + 1) +1 for main including main item,
            List<String> allIngredients = beveragesMap.get(itemIngredients.get(0)); // Beverage name is always at index 0
            if (itemIngredients.size() == allIngredients.size() + 1)
                return false;
        }
        return true;
    }


    //     get Item and exclusion prices from DataLoader and return cost for each item in order
    public Double calculateInvoice(List<String> itemWithIngredients) {
        Double itemValue = itemRates.get(itemWithIngredients.get(0));
        Double ingredientsValue = 0.0d;
        // check to ensure items are there in list
        List<String> ingredients = itemWithIngredients.subList(1, itemWithIngredients.size());
        List<String> allIngredients = beveragesMap.get(itemWithIngredients.get(0));

        // it checks if an invalid ingredient is passed to the order
        boolean validIngredients = ingredients.stream().allMatch(t -> allIngredients.stream().anyMatch(t::contains));
        if (!validIngredients)
            throw new IllegalIngredientsException("Invalid ingredient in order");

        if (itemWithIngredients.size() > 1)
            for (int i = 1; i < itemWithIngredients.size(); i++) {
                ingredientsValue = ingredientsValue + ingredientRates.get(itemWithIngredients.get(i));
            }
        return itemValue - ingredientsValue;
    }

}
