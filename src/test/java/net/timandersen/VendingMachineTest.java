package net.timandersen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {


    @org.junit.Test
    public void trackProductQuantity() {
        VendingMachine vendingMachine = new VendingMachine();

        Product snickers = new Product("A1", "Snickers", 0.75);
        Product mms = new Product("A2", "M&Ms", 0.65);

        vendingMachine.addProducts(snickers, 20);
        vendingMachine.addProducts(mms, 15);

        vendingMachine.releaseProduct(snickers.getCode());
        vendingMachine.releaseProduct(mms.getCode());

        assertEquals(19, vendingMachine.getQuantityFor(snickers.getCode()));
        assertEquals(14, vendingMachine.getQuantityFor(mms.getCode()));
    }

    @Test
    public void makeChangeForOverPayment() {
        VendingMachine vendingMachine = new VendingMachine();
        Double change = vendingMachine.acceptPayment(new Product("A1", "Snickers", 0.75), 1.00);
        assertEquals(new Double(0.25), change);
    }

}
