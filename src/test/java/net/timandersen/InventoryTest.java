package net.timandersen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryTest {

  @Test
  public void addProducts() {
    Inventory inventory = new Inventory();
    inventory.addProducts(new Product("A1", "Snickers", 0.75), 20);
    assertEquals(20, inventory.getQuantityFor("A1"));
  }

  @Test
  public void removeProduct() {
    Inventory inventory = new Inventory();
    inventory.addProducts(new Product("A1", "Snickers", 0.75), 20);
    inventory.removeProduct("A1");
    inventory.removeProduct("A1");
    inventory.removeProduct("A1");
    assertEquals(17, inventory.getQuantityFor("A1"));
  }

}
