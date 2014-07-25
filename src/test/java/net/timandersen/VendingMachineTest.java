package net.timandersen;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {


  private VendingMachine vendingMachine;
  private Product snickers;
  private Product mms;


  @Before
  public void setUp() {
    vendingMachine = new VendingMachine();
    snickers = new Product("A1", "Snickers", 0.75);
    mms = new Product("A2", "M&Ms", 0.65);
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
    vendingMachine.addProducts(snickers, 20);
    vendingMachine.addProducts(mms, 15);

    vendingMachine.dispenseProduct(snickers.getCode());
    vendingMachine.dispenseProduct(mms.getCode());

    assertEquals(19, vendingMachine.getQuantityFor(snickers.getCode()));
    assertEquals(14, vendingMachine.getQuantityFor(mms.getCode()));
  }

  @Test
  public void makeChangeForOverPayment() {
    vendingMachine.addProducts(snickers, 20);
    vendingMachine.acceptMoney(1.00);
    vendingMachine.purchase(snickers);
    Double change = vendingMachine.ejectChange();
    assertEquals(new Double(0.25), change);
  }

  @Test
  public void purchaseSubtractsFromCredit() {
    vendingMachine.addProducts(snickers, 20);
    vendingMachine.acceptMoney(0.85);
    vendingMachine.purchase(snickers);
    assertEquals("$0.10", vendingMachine.showCredit());
  }

  @Test
  public void notEnoughCreditForPurchase() {
    vendingMachine.addProducts(snickers, 20);
    vendingMachine.acceptMoney(0.50);
    vendingMachine.purchase(snickers);
    assertEquals(20, vendingMachine.getQuantityFor(snickers.getCode()));
    assertEquals("$0.50", vendingMachine.showCredit());
  }

  @Test
  public void trackMoney() {
    vendingMachine.setCashAmount(10.00);
    vendingMachine.addProducts(snickers, 20);
    vendingMachine.acceptMoney(1.00);
    vendingMachine.purchase(snickers);
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
    vendingMachine.addProducts(snickers, 0);
    vendingMachine.addProducts(mms, 3);
    vendingMachine.acceptMoney(0.75);
    vendingMachine.purchase(snickers);
    vendingMachine.purchase(mms);
    assertEquals(new Double(0.10), vendingMachine.ejectChange());
    assertEquals(0, vendingMachine.getQuantityFor(snickers.getCode()));
    assertEquals(2, vendingMachine.getQuantityFor(mms.getCode()));
  }

}
