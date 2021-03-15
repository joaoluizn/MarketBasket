package core.dto;

import core.models.Product;

import java.math.BigInteger;

public class BuyOrder {

    Product product;
    BigInteger quantity;

    public BuyOrder(Product product, BigInteger quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

}
