package net.timandersen;

public enum Product {

  SNICKERS("A1", "Snickers", 0.75),
  MMS("A2", "M&M's", 0.65),
  BUTTERFINGER("A3", "Butterfinger", 0.85);

  private String code;
  private String name;
  private double price;

  Product(String code, String name, Double price) {
    this.code = code;
    this.name = name;
    this.price = price;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public Double getPrice() {
    return price;
  }

  public static Product findBy(String code) {
    for (Product product : values()) {
      if (product.getCode().equals(code)) {
        return product;
      }
    }
    return null;
  }
}
