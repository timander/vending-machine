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
    vendingMachine.chooseProduct(snickers);
    Double change = vendingMachine.ejectChange();
    assertEquals(new Double(0.25), change);
  }

  @Test
  public void chooseProductSubtractsFromCredit() {
    vendingMachine.addProducts(snickers, 20);
    vendingMachine.acceptMoney(0.85);
    vendingMachine.chooseProduct(snickers);
    assertEquals("$0.10", vendingMachine.showCredit());
  }

  @Test
  public void chooseProductNotEnoughCredit() {
    vendingMachine.addProducts(snickers, 20);
    vendingMachine.acceptMoney(0.50);
    vendingMachine.chooseProduct(snickers);
    assertEquals(20, vendingMachine.getQuantityFor(snickers.getCode()));
    assertEquals("$0.50", vendingMachine.showCredit());
  }

  @Test
  public void trackMoney() {
    vendingMachine.setCashAmount(10.00);
    vendingMachine.addProducts(snickers, 20);
    vendingMachine.acceptMoney(1.00);
    vendingMachine.chooseProduct(snickers);
    assertEquals(new Double(10.75), vendingMachine.getCashAmount());
  }

}
