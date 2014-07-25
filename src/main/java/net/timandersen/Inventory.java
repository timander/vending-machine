package net.timandersen;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

  List<Product> stock = new ArrayList<Product>();

  public void addProducts(Product product, int quantity) {
    for (int i = 0; i < quantity; i++) {
      stock.add(product);
    }
  }

  public int getQuantityFor(String code) {
    int quantity = 0;
    for (Product product : stock) {
      if (product.getCode().equals(code)) {
        quantity++;
      }
    }
    return quantity;
  }

  public void removeProduct(String code) {
    for (Product product : stock) {
      if (product.getCode().equals(code)) {
        stock.remove(product);
        break;
      }
    }
  }
}
