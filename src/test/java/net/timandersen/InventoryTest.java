package net.timandersen;

import org.junit.Test;

import static net.timandersen.Product.*;
import static org.junit.Assert.assertEquals;

public class InventoryTest {

  @Test
  public void addProducts() {
    Inventory inventory = new Inventory();
    inventory.addProducts(SNICKERS, 20);
    assertEquals(20, inventory.getQuantityFor("A1"));
  }

  @Test
  public void removeProduct() {
    Inventory inventory = new Inventory();
    inventory.addProducts(SNICKERS, 20);
    inventory.removeProduct("A1");
    inventory.removeProduct("A1");
    inventory.removeProduct("A1");
    assertEquals(17, inventory.getQuantityFor("A1"));
  }

  @Test
  public void getPriceForProduct() {
    Inventory inventory = new Inventory();
    inventory.addProducts(SNICKERS, 1);
    assertEquals(new Double(0.75), inventory.getPriceFor("A1"));
  }

  @Test
  public void getPriceForProductNotInInventory() {
    Inventory inventory = new Inventory();
    inventory.addProducts(SNICKERS, 0);
    assertEquals(new Double(0.75), inventory.getPriceFor("A1"));
  }

}
