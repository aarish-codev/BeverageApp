package com.coditas.app;


public class BeverageApp {

    public static void main(String[] args) throws Exception {

        BeverageFactory factory = new BeverageFactory();

        Double cost = factory.getInvoiceFromOrder("Chai, -milk, -water");
//        Double cost = factory.getInvoiceFromOrder("Chai, -milk, -water, Chai, Coffee, -milk, -sugar, -water");
        System.out.println("Your total cost is $" + cost);

    }


}
