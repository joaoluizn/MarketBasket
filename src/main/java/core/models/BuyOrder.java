package core.models;

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

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }
}
