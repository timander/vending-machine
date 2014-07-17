package net.timandersen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public Double acceptPayment(Product product, Double payment) {
        return payment - product.getPrice();
    }

    public List<String> notifyOperator() {
        List<String> warnings = new ArrayList<String>();
        for (Map.Entry<String, Integer> item : inventory.entrySet()) {
            if (item.getValue() <= 5 && item.getValue() > 0) {
                warnings.add("The vending machine only has " + item.getValue() + " Snickers left");
            }
            if (item.getValue() == 0) {
                warnings.add("The vending machine out of M&Ms");
            }
        }
        return warnings;
    }
}
