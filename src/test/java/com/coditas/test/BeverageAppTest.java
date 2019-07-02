package com.coditas.test;

import com.coditas.test.exception.IllegalIngredientsException;
import com.coditas.test.factory.BeverageFactory;
import com.coditas.test.exception.BeverageTypoException;
import com.coditas.test.exception.InvalidOrderException;
import org.junit.Assert;
import org.junit.Test;


public class BeverageAppTest {


    BeverageFactory factory = new BeverageFactory();


    @Test(expected = BeverageTypoException.class)
    public void testForBlankOrder() {
        String order = "";
        Assert.assertEquals(0.0d, factory.getInvoiceFromOrder(order), 0.0d);
    }

    @Test
    public void testForNormalOrder1() {
        Assert.assertEquals(2.5d, factory.getInvoiceFromOrder("Chai, -milk, -water"), 0.0d);
    }

    @Test()
    public void testForNormalOrder2() {
        String order = "Chai, -milk, -water, Mojito, Banana Smoothie, Strawberry Shake";
        Assert.assertEquals(23d, factory.getInvoiceFromOrder(order), 0.0d);
    }


    @Test
    public void testForOrderWithNoExclusions() {
        String order = "Chai, Coffee";
        Assert.assertEquals(9.0d, factory.getInvoiceFromOrder(order), 0.0d);
    }

    @Test(expected = InvalidOrderException.class)
    public void testForOrderWithAllExclusions() {
        String order = "Coffee, -milk, -sugar, -water";
        Assert.assertEquals(0.0d, factory.getInvoiceFromOrder(order), 0.0d);
    }


    @Test(expected = BeverageTypoException.class)
    public void testOrderWithInvalidOrder() {
        String order = "paani,tee";
        Assert.assertEquals(0.0d, factory.getInvoiceFromOrder(order), 0.0d);
    }

    @Test(expected = IllegalIngredientsException.class)
    public void testIllegalIngredientInOrder() {
        String order = "Chai, -money, -water, Coffee, Mojito";
        Assert.assertEquals(0.0d, factory.getInvoiceFromOrder(order), 0.0d);
    }

}