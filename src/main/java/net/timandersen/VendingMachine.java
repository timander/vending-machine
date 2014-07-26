package net.timandersen;

public class VendingMachine {

  private Inventory inventory = new Inventory();
  private Double credit = 0.0;
  private Double cashAmount = 0.0;

  public void dispenseProduct(String productCode) {
    inventory.removeProduct(productCode);
  }

  public void addProducts(Product product, int quantity) {
    inventory.addProducts(product, quantity);
  }

  public int getQuantityFor(String productCode) {
    return inventory.getQuantityFor(productCode);
  }

  public void acceptMoney(Double money) {
    this.credit = money;
  }

  public String showCredit() {
    return "$" + String.format("%.2f", credit);
  }

  public void purchase(String code) {
    if (getQuantityFor(code) > 0) {
      Double price = inventory.getPriceFor(code);
      if (credit >= price) {
        credit = credit - price;
        cashAmount += price;
        dispenseProduct(code);
      }
    }
  }

  public Double ejectChange() {
    Double change = credit;
    credit = 0.0;
    return new Double(String.format("%.2f", change));
  }

  public void setCashAmount(double cashAmount) {
    this.cashAmount = cashAmount;
  }

  public Double getCashAmount() {
    return cashAmount;
  }

}
