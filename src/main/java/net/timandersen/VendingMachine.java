package net.timandersen;

public class VendingMachine {

  private Inventory inventory = new Inventory();
  Double credit = 0.0;

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
