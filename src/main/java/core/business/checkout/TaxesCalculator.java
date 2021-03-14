package core.business.checkout;

import core.models.BuyOrder;
import core.models.Product;
import core.models.TaxedOrder;
import core.models.TaxedProduct;
import core.models.enums.ProductType;
import utils.MathUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.MathUtils.bigDecimal;

/**
 * Entity responsible for calculating products taxes
 */
public class TaxesCalculator {

    private static final BigDecimal ROUNDUP_FACTOR = bigDecimal("0.05");
    private static final BigDecimal IMPORTATION_FACTOR = bigDecimal("0.05");
    private static final BigDecimal TRADE_GOODS_FACTOR = bigDecimal("0.1");
    private static List<ProductType> EXEMPT_TYPES = Arrays.asList(ProductType.BOOK, ProductType.MEDICINE, ProductType.FOOD);

    /**
     * Gets the taxed products from buy order list.
     *
     * @param basket the List of BuyOrder
     * @return the taxed Order list
     */
    public List<TaxedOrder> getTaxedProducts(List<BuyOrder> basket) {
        List<TaxedOrder> taxedOrders = new ArrayList<>();
        for (BuyOrder buyOrder : basket) {
            Product product = buyOrder.getProduct();
            BigInteger quantity = buyOrder.getQuantity();
            BigDecimal goods = this.calculateProductImportTax(product);
            BigDecimal imports = this.calculateProductGoodsTax(product);
            BigDecimal taxedPrice = product.getPrice().add(goods).add(imports);
            TaxedProduct taxedProduct = new TaxedProduct(product, taxedPrice, goods, imports);
            taxedOrders.add(new TaxedOrder(taxedProduct, quantity));
        }
        return taxedOrders;
    }

    /**
     * Gets total taxes from a taxed basket
     *
     * @param taxedBasket The list of taxed order
     * @return the total taxes
     */
    public BigDecimal getTotalTaxes(List<TaxedOrder> taxedBasket) {
        BigDecimal totalTaxes = bigDecimal("0.00");

        for (TaxedOrder taxedOrder : taxedBasket) {
            TaxedProduct product = taxedOrder.getProduct();
            BigInteger quantity = taxedOrder.getQuantity();
            BigDecimal totalProductTaxes = this.getTotalTaxesFromProduct(product).multiply(bigDecimal(quantity));
            totalTaxes = totalTaxes.add(totalProductTaxes);
        }
        return totalTaxes;
    }

    /**
     * Gets total price with taxes from a taxed basket.
     *
     * @param taxedBasket The list of taxed order
     * @return the total price with taxes
     */
    public BigDecimal getTotalPriceWithTaxes(List<TaxedOrder> taxedBasket) {
        BigDecimal taxedTotalPrice = bigDecimal("0.00");

        for (TaxedOrder taxedOrder : taxedBasket) {
            TaxedProduct product = taxedOrder.getProduct();
            BigInteger quantity = taxedOrder.getQuantity();
            BigDecimal totalProductValue = product.getTaxedPrice().multiply(bigDecimal(quantity));
            taxedTotalPrice = taxedTotalPrice.add(totalProductValue);
        }
        return taxedTotalPrice;
    }

    /**
     * Gets total taxes for a single product
     *
     * @param product the {@link Product}
     * @return the product total taxes
     */
    private BigDecimal getTotalTaxesFromProduct(Product product) {
        BigDecimal goodTax = this.calculateProductGoodsTax(product);
        BigDecimal importTax = this.calculateProductImportTax(product);
        return goodTax.add(importTax);
    }

    /**
     * Gets product importation tax from a single product.
     *
     * @param product the {@link Product}
     * @return the product importation tax
     */
    private BigDecimal calculateProductImportTax(Product product) {
        BigDecimal tax = new BigDecimal("0.00");
        BigDecimal productPrice = product.getPrice();
        if (product.isImported()) {
            tax = IMPORTATION_FACTOR.multiply(productPrice);
        }
        return MathUtils.round(tax, ROUNDUP_FACTOR, RoundingMode.UP);
    }

    /**
     * Gets product good tax from a single product.
     *
     * @param product the {@link Product}
     * @return the product goods tax
     */
    private BigDecimal calculateProductGoodsTax(Product product) {
        BigDecimal tax = TRADE_GOODS_FACTOR.multiply(product.getPrice());
        if (EXEMPT_TYPES.contains(product.getType())) {
            tax = new BigDecimal("0.00");
        }
        return MathUtils.round(tax, ROUNDUP_FACTOR, RoundingMode.UP);
    }

}
