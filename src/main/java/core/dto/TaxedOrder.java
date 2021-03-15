package core.dto;

import java.math.BigInteger;

public class TaxedOrder {

    private TaxedProduct product;
    private BigInteger quantity;

    public TaxedOrder(TaxedProduct product, BigInteger quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public TaxedProduct getProduct() {
        return product;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

}
