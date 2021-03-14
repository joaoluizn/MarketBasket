package core.models;

import core.models.enums.ProductType;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal price;
    private ProductType type;
    private boolean isImported;

    public Product(String name, BigDecimal price, boolean isImported, ProductType type) {
        this.name = name;
        this.price = price;
        this.isImported = isImported;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }

    public boolean isImported() {
        return isImported;
    }

}
