package core.models;

import java.math.BigDecimal;

public class TaxedProduct extends Product {

    private BigDecimal goodTax;
    private BigDecimal importTax;
    private BigDecimal taxedPrice;

    public TaxedProduct(Product product, BigDecimal taxedPrice, BigDecimal goodTax, BigDecimal importTax) {
        super(product.getName(), product.getPrice(), product.isImported(), product.getType());
        this.taxedPrice = taxedPrice;
        this.goodTax = goodTax;
        this.importTax = importTax;
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public BigDecimal getGoodTax() {
        return goodTax;
    }

    public void setGoodTax(BigDecimal goodTax) {
        this.goodTax = goodTax;
    }

    public BigDecimal getImportTax() {
        return importTax;
    }

    public void setImportTax(BigDecimal importTax) {
        this.importTax = importTax;
    }
}
