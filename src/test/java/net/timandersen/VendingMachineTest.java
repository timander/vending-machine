package net.timandersen;

import org.junit.Before;
import org.junit.Test;

import static net.timandersen.Product.*;
import static org.junit.Assert.assertEquals;

public class VendingMachineTest {


  private VendingMachine vendingMachine;


  @Before
  public void setUp() {
    vendingMachine = new VendingMachine();
  }


  @Test
  public void showCreditWhenUserInsertsDollar() {
    vendingMachine.acceptMoney(1.00);
    assertEquals("$1.00", vendingMachine.showCredit());
  }

  @Test
  public void showCreditWhenUserInsertsFiftyCents() {
    vendingMachine.acceptMoney(0.50);
    assertEquals("$0.50", vendingMachine.showCredit());
  }

  @Test
  public void trackProductQuantity() {
    vendingMachine.addProducts(SNICKERS, 20);
    vendingMachine.addProducts(MMS, 15);

    vendingMachine.dispenseProduct(SNICKERS.getCode());
    vendingMachine.dispenseProduct(MMS.getCode());

    assertEquals(19, vendingMachine.getQuantityFor(SNICKERS.getCode()));
    assertEquals(14, vendingMachine.getQuantityFor(MMS.getCode()));
  }

  @Test
  public void makeChangeForOverPayment() {
    vendingMachine.addProducts(SNICKERS, 20);
    vendingMachine.acceptMoney(1.00);
    vendingMachine.purchase(SNICKERS.getCode());
    Double change = vendingMachine.ejectChange();
    assertEquals(new Double(0.25), change);
  }

  @Test
  public void purchaseSubtractsFromCredit() {
    vendingMachine.addProducts(SNICKERS, 20);
    vendingMachine.acceptMoney(0.85);
    vendingMachine.purchase(SNICKERS.getCode());
    assertEquals("$0.10", vendingMachine.showCredit());
  }

  @Test
  public void notEnoughCreditForPurchase() {
    vendingMachine.addProducts(SNICKERS, 20);
    vendingMachine.acceptMoney(0.50);
    vendingMachine.purchase(SNICKERS.getCode());
    assertEquals(20, vendingMachine.getQuantityFor(SNICKERS.getCode()));
    assertEquals("$0.50", vendingMachine.showCredit());
  }

  @Test
  public void trackMoney() {
    vendingMachine.setCashAmount(10.00);
    vendingMachine.addProducts(SNICKERS, 20);
    vendingMachine.acceptMoney(1.00);
    vendingMachine.purchase(SNICKERS.getCode());
    assertEquals(new Double(10.75), vendingMachine.getCashAmount());
  }

  @Test
  public void cancelPurchase() {
    vendingMachine.acceptMoney(0.85);
    Double change = vendingMachine.ejectChange();
    assertEquals(new Double(0.85), change);
  }

  @Test
  public void purchaseItemNotInInventory() {
    vendingMachine.addProducts(SNICKERS, 0);
    vendingMachine.addProducts(MMS, 3);
    vendingMachine.acceptMoney(0.75);
    vendingMachine.purchase(SNICKERS.getCode());
    vendingMachine.purchase(MMS.getCode());
    assertEquals(new Double(0.10), vendingMachine.ejectChange());
    assertEquals(0, vendingMachine.getQuantityFor(SNICKERS.getCode()));
    assertEquals(2, vendingMachine.getQuantityFor(MMS.getCode()));
  }

}
