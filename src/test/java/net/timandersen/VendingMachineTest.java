package net.timandersen;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {


    @org.junit.Test
    public void trackProductQuantity() {
        VendingMachine vendingMachine = new VendingMachine();

        Product snickers = new Product("A1", "Snickers");
        Product mms = new Product("A2", "M&Ms");

        vendingMachine.addProducts(snickers, 20);
        vendingMachine.addProducts(mms, 15);

        vendingMachine.releaseProduct(snickers.getCode());
        vendingMachine.releaseProduct(mms.getCode());

        assertEquals(19, vendingMachine.getQuantityFor(snickers.getCode()));
        assertEquals(14, vendingMachine.getQuantityFor(mms.getCode()));
    }

}
