package core.business.checkout;

import core.models.BuyOrder;
import core.models.Product;
import core.models.TaxedProduct;
import core.models.enums.ProductType;
import utils.MathUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.MathUtils.bigDecimal;

/**
 * Tax Calculator Component.
 */
public class TaxesCalculator {

    private static final BigDecimal ROUNDUP_FACTOR = bigDecimal("0.05");
    private static final BigDecimal IMPORTATION_FACTOR = bigDecimal("0.05");
    private static final BigDecimal TRADE_GOODS_FACTOR = bigDecimal("0.1");
    private static List<ProductType> EXEMPT_TYPES = Arrays.asList(ProductType.BOOK, ProductType.MEDICINE, ProductType.FOOD);

    /**
     * Gets the taxed products from buy order list.
     *
     * @param basket {@link List<Product>} the List of Products
     * @return the map of taxed products and quantity
     */
    public HashMap<TaxedProduct, BigInteger> getTaxedProducts(List<BuyOrder> basket) {
        HashMap<TaxedProduct, BigInteger> taxedBasket = new HashMap<>();
        for (BuyOrder buyOrder : basket) {
            Product product = buyOrder.getProduct();
            BigInteger quantity = buyOrder.getQuantity();
            BigDecimal goods = this.calculateProductImportTax(product);
            BigDecimal imports = this.calculateProductGoodsTax(product);
            BigDecimal taxedPrice = product.getPrice().add(goods).add(imports);
            TaxedProduct taxedProduct = new TaxedProduct(product, taxedPrice, goods, imports);
            taxedBasket.put(taxedProduct, quantity);
        }
        return taxedBasket;
    }

    /**
     * Gets total taxes from a taxed basket
     *
     * @param taxedBasket {@link List<TaxedProduct>} the taxed products
     * @return the total taxes
     */
    public BigDecimal getTotalTaxes(HashMap<TaxedProduct, BigInteger> taxedBasket) {
        BigDecimal totalTaxes = bigDecimal("0.00");

        for (Map.Entry<TaxedProduct, BigInteger> entry : taxedBasket.entrySet()) {
            TaxedProduct product = entry.getKey();
            BigInteger quantity = entry.getValue();
            BigDecimal totalProductTaxes = this.getTotalTaxesFromProduct(product).multiply(bigDecimal(quantity));
            totalTaxes = totalTaxes.add(totalProductTaxes);
        }
        return totalTaxes;
    }

    /**
     * Gets total price with taxes from a taxed basket.
     *
     * @param taxedBasket {@link List<TaxedProduct>} the taxed products
     * @return the total price with taxes
     */
    public BigDecimal getTotalPriceWithTaxes(HashMap<TaxedProduct, BigInteger> taxedBasket) {
        BigDecimal taxedTotalPrice = bigDecimal("0.00");

        for (Map.Entry<TaxedProduct, BigInteger> entry : taxedBasket.entrySet()) {
            TaxedProduct product = entry.getKey();
            BigInteger quantity = entry.getValue();
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
