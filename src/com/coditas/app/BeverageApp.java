package com.coditas.app;

import java.util.*;
import java.util.stream.Collectors;

public class BeverageApp {

    static Map<String, List<String>> beveragesMap;
    static Map<String, Double> itemRates;
    static Map<String, Double> ingredientRates;


    public static void main(String[] args) throws Exception {

        BeverageApp app = new BeverageApp();
        beveragesMap = new HashMap<>();
        beveragesMap.put("coffee", Arrays.asList("milk", "sugar", "water"));
        beveragesMap.put("chai", Arrays.asList("milk", "sugar", "water"));
        beveragesMap.put("banana smoothie", Arrays.asList("milk", "sugar", "water"));
        beveragesMap.put("Strawberry shake", Arrays.asList("sugar", "milk", "water"));
        beveragesMap.put("mojito", Arrays.asList("sugar", "water", "soda", "mint"));

        itemRates = new HashMap<>();
        itemRates.put("coffee", 5.0d);
        itemRates.put("chai", 4.0d);
        itemRates.put("banana smoothie", 6.0d);
        itemRates.put("strawberry shake", 7.0d);
        itemRates.put("mojito", 7.5d);

        ingredientRates = new HashMap<>();
        ingredientRates.put("milk", 1.0d);
        ingredientRates.put("sugar", 0.5d);
        ingredientRates.put("soda", 0.5d);
        ingredientRates.put("mint", 0.5d);
        ingredientRates.put("water", 0.5d);


//        String order =;
//        String order = ;
//        String order =;
//        String order =;

        List<String> orders = new ArrayList<>();
        orders.add("Chai, -milk, -water");
        orders.add("Chai, Coffee, Mojito");
        orders.add("Chai, -sugar, -milk");
//        orders.add("Chai, -milk, -water, Chai, Coffee, -milk, -sugar, -water");
//        orders.add("");

        for (String order : orders) {
            Double cost = 0.0d;
            List<String> orderItems = app.getItemsFromOrder(order.trim());
            for (String item : orderItems) {
//            System.out.println("****************************************");
                List<String> itemWithIngredients = app.getIngredientFromItem(item);
                boolean isValidOrder = app.checkIfValidOrder(item);
//            System.out.println("\nisValidOrder: " + isValidOrder);
                if (isValidOrder) {
                    cost = cost + app.calculateInvoice(itemWithIngredients);
                } else {
                    throw new Exception("Invalid Order..!! You cannot exclude all items from order -> " + item);
                }

            }
            System.out.println("Your total cost is $" + cost);
        }

        System.out.println("###################################################");
        BeverageFactory factory = new BeverageFactory();
        factory.getInvoiceFromOrder("Chai, -milk, -water");
        factory.getInvoiceFromOrder("Chai, -milk, -water, Chai, Coffee, -milk, -sugar, -water");


    }


    public List<String> getItemsFromOrder(String order1) {
        List<String> items = Arrays.stream(order1.split("(?!, -),")).map(String::trim).map(String::toLowerCase).collect(Collectors.toList());

        return items;
    }

    public boolean checkIfValidOrder(String item) throws Exception {

        List<String> itemIngredients = getIngredientFromItem(item);
//        System.out.println("{Ordered Item Ingredients}");
//        itemIngredients.forEach(s-> System.out.print(s+" "));
        // case: When typo in order
        if (item.length() < 1 || (!beveragesMap.containsKey(itemIngredients.get(0))))
            throw new Exception("Typo in order, try again");
            // order has no typo, now check if all ingredients are not removed
        else {
            List<String> allIngredients = beveragesMap.get(itemIngredients.get(0)); // Beverage name is always at index 0
//            System.out.println("\n{All ingredients}:");
//            allIngredients.forEach(s-> System.out.print(s+" "));
            if (itemIngredients.size() == allIngredients.size() + 1)
                return false;
        }
        return true;
    }


    public List<String> getIngredientFromItem(String item) {
//        trimming and removing '-'
        return Arrays.stream(item.split(",")).map(s -> s.replace("-", "")).map(String::trim).collect(Collectors.toList());
    }

    public Double calculateInvoice(List<String> itemWithIngredients) {


        Double itemValue = itemRates.get(itemWithIngredients.get(0));
        Double ingredientsValue = 0.0d;
        int size = itemWithIngredients.size();
        if (size > 1)
            for (int i = 1; i < size; i++) {
                ingredientsValue = ingredientsValue + ingredientRates.get(itemWithIngredients.get(i));
            }
//        System.out.println("Item cost ->"+ (itemValue - ingredientsValue) );
        return itemValue - ingredientsValue;
    }

}
