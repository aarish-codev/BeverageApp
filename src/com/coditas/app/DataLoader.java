package com.coditas.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {

    private static final String SUGAR = "sugar";
    private static final String WATER = "water";

    private DataLoader() {

    }

    public static Map<String, List<String>> getBeveragesMap() {
        Map<String, List<String>> beveragesMap;
        beveragesMap = new HashMap<>();
        beveragesMap.put("coffee", Arrays.asList("milk", SUGAR, WATER));
        beveragesMap.put("chai", Arrays.asList("milk", SUGAR, WATER));
        beveragesMap.put("banana smoothie", Arrays.asList("milk", SUGAR, WATER));
        beveragesMap.put("Strawberry shake", Arrays.asList(WATER, "milk", WATER));
        beveragesMap.put("mojito", Arrays.asList(SUGAR, WATER, "soda", "mint"));

        return beveragesMap;
    }


    public static Map<String, Double> getItemRates() {
        Map<String, Double> itemRates;
        itemRates = new HashMap<>();
        itemRates.put("coffee", 5.0d);
        itemRates.put("chai", 4.0d);
        itemRates.put("banana smoothie", 6.0d);
        itemRates.put("strawberry shake", 7.0d);
        itemRates.put("mojito", 7.5d);
        return itemRates;
    }


    public static Map<String, Double> getIngredientRates() {
        Map<String, Double> ingredientRates;
        ingredientRates = new HashMap<>();
        ingredientRates.put("milk", 1.0d);
        ingredientRates.put(SUGAR, 0.5d);
        ingredientRates.put("soda", 0.5d);
        ingredientRates.put("mint", 0.5d);
        ingredientRates.put(WATER, 0.5d);
        return ingredientRates;
    }


}

