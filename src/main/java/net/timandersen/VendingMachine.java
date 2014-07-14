package net.timandersen;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    Map<String, Integer> inventory = new HashMap<String, Integer>();

    public void releaseProduct(String productCode) {
        Integer currentQuantity = getQuantityFor(productCode);
        inventory.put(productCode, currentQuantity - 1);
    }

    public void addProducts(Product product, int quantity) {
        inventory.put(product.getCode(), quantity);
    }

    public int getQuantityFor(String productCode) {
        return inventory.get(productCode);
    }
}
