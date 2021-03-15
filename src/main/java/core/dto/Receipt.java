package core.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class Receipt {

    List<TaxedOrder> taxedOrders;
    private BigDecimal allTaxes;
    private BigDecimal totalWithTaxes;

    public Receipt(List<TaxedOrder> taxedOrders, BigDecimal totalWithTaxes, BigDecimal allTaxes) {
        this.taxedOrders = taxedOrders;
        this.totalWithTaxes = totalWithTaxes;
        this.allTaxes = allTaxes;
    }

    public List<TaxedOrder> getTaxedOrders() {
        return taxedOrders;
    }

    public void setTaxedProductsHashMap(List<TaxedOrder> taxedOrders) {
        this.taxedOrders = taxedOrders;
    }

    public BigDecimal getAllTaxes() {
        return allTaxes;
    }

    public void setAllTaxes(BigDecimal allTaxes) {
        this.allTaxes = allTaxes;
    }

    public BigDecimal getTotalWithTaxes() {
        return totalWithTaxes;
    }

    public void setTotalWithTaxes(BigDecimal totalWithTaxes) {
        this.totalWithTaxes = totalWithTaxes;
    }


    private String createReceiptItemsDescription() {
        StringBuilder description = new StringBuilder();
        for (TaxedOrder taxedOrder : this.taxedOrders) {
            TaxedProduct product = taxedOrder.getProduct();
            BigInteger quantity = taxedOrder.getQuantity();
            String importedString = product.isImported() ? " imported " : " ";
            BigDecimal totalPrice = product.getTaxedPrice().multiply(new BigDecimal(quantity));
            description.append(String.format("%s%s%s: %s\n", quantity, importedString, product.getName(), totalPrice));
        }
        return description.toString();
    }

    public String toString() {
        StringBuilder receipt = new StringBuilder();
        String description = this.createReceiptItemsDescription();
        String allTaxes = String.format("Sales Taxes: %s\n", this.allTaxes);
        String total = String.format("Total: %s\n", this.totalWithTaxes);
        receipt.append(description);
        receipt.append(allTaxes);
        receipt.append(total);
        return receipt.toString();
    }
}
