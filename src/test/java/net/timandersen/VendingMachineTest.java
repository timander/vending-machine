package net.timandersen;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {


    private VendingMachine vendingMachine;
    private Product snickers;
    private Product mms;


    @Before
    public void setUp() {
        vendingMachine = new VendingMachine();
        snickers = new Product("A1", "Snickers", 0.75);
        mms = new Product("A2", "M&Ms", 0.65);
    }

    @Test
    public void trackProductQuantity() {
        vendingMachine.addProducts(snickers, 20);
        vendingMachine.addProducts(mms, 15);

        vendingMachine.releaseProduct(snickers.getCode());
        vendingMachine.releaseProduct(mms.getCode());

        assertEquals(19, vendingMachine.getQuantityFor(snickers.getCode()));
        assertEquals(14, vendingMachine.getQuantityFor(mms.getCode()));
    }

    @Test
    public void makeChangeForOverPayment() {
        Double change = vendingMachine.acceptPayment(new Product("A1", "Snickers", 0.75), 1.00);
        assertEquals(new Double(0.25), change);
    }

    @Test
    public void zeroChangeForExactPayment() {
        Double change = vendingMachine.acceptPayment(new Product("A1", "Snickers", 0.75), 0.75);
        assertEquals(new Double(0.0), change);
    }

    @Test
    public void calculateRemainingBalanceForUnderPayment() {
        Double change = vendingMachine.acceptPayment(new Product("A1", "Snickers", 0.75), 0.25);
        assertEquals(new Double(-0.50), change);
    }


    @Test
    public void notifyOperatorWhenInventoryForProductIsFiveOrLess() {
        vendingMachine.addProducts(snickers, 5);
        vendingMachine.addProducts(mms, 0);
        List<String> warnings = vendingMachine.notifyOperator();
        assertEquals(2, warnings.size());
        assertEquals("The vending machine out of M&Ms", warnings.get(0));
        assertEquals("The vending machine only has 5 Snickers left", warnings.get(1));
    }


}
