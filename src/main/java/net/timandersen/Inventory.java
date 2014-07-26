package net.timandersen;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

  private Map<String, Integer> stock = new HashMap<String, Integer>();

  public void addProducts(Product product, int quantity) {
    stock.put(product.getCode(), quantity);
  }

  public int getQuantityFor(String code) {
    return stock.get(code);
  }

  public void removeProduct(String code) {
    stock.put(code, getQuantityFor(code) - 1);
  }

  public Double getPriceFor(String code) {
    return Product.findBy(code).getPrice();
  }

}
