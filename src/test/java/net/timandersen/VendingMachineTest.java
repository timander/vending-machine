package net.timandersen;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {


    @org.junit.Test
    public void trackProductQuantity() {
        VendingMachine vendingMachine = new VendingMachine();
        Product product = new Product("A1", "Snickers");
        vendingMachine.addProducts(product, 20);
        vendingMachine.releaseProduct("A1");
        assertEquals(19, vendingMachine.getQuantityFor(product.getCode()));
    }

}
