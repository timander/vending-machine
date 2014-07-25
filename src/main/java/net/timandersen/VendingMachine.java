package net.timandersen;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

  Map<String, Integer> inventory = new HashMap<String, Integer>();
  Double credit = 0.0;

  public void dispenseProduct(String productCode) {
    Integer currentQuantity = getQuantityFor(productCode);
    inventory.put(productCode, currentQuantity - 1);
  }

  public void addProducts(Product product, int quantity) {
    inventory.put(product.getCode(), quantity);
  }

  public int getQuantityFor(String productCode) {
    return inventory.get(productCode);
  }

  public void acceptMoney(Double money) {
    this.credit = money;
  }

  public String showCredit() {
    return "$" + String.format("%.2f", credit);
  }

  public void chooseProduct(Product product) {
    if (credit >= product.getPrice()) {
      credit = credit - product.getPrice();
      dispenseProduct(product.getCode());
    }
  }

  public Double ejectChange() {
    Double change = credit;
    credit = 0.0;
    return change;
  }
}
