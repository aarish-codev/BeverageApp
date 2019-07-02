package com.coditas.app;


import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class BeverageAppTest {

    @org.junit.Test
    public void test() throws Exception {
        String order = "Chai, -milk, -water";
        BeverageApp app = new BeverageApp();
        List<String> orderItems = app.getItemsFromOrder(order);
        assertNotNull(app.getItemsFromOrder(order));
        for (String s : orderItems) {
            List<String> itemWithIngredients = app.getIngredientFromItem(s);
            assertNotNull(app.getIngredientFromItem(s));
            //boolean isValidOrder = app.checkIfValidOrder(s);


        }

    }

}