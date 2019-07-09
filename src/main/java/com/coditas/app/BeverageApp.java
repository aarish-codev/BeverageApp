package com.coditas.app;


import com.coditas.app.factory.BeverageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeverageApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeverageApp.class);

    public static void main(String[] args) {

        BeverageFactory factory = new BeverageFactory();

//        Modify the below string and run the program to calculate your order cost
        String order = " ,-milk, -water,  Coffee, Mojito";

        final double cost = factory.getInvoiceFromOrder(order);

        LOGGER.info("Your total cost is ${}", cost);

    }


}
