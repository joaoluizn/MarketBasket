package core.business.checkout;

import core.models.Product;
import core.models.TaxedProduct;
import core.models.enums.ProductType;
import utils.MathUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Gets the taxed products from a list of Products.
     *
     * @param products {@link List<Product>} the List of Products
     * @return the taxed products
     */
    public List<TaxedProduct> getTaxedProducts(List<Product> products) {
        List<TaxedProduct> taxedProducts = new ArrayList<>();
        for (Product product : products) {
            BigDecimal goods = this.calculateProductImportTax(product);
            BigDecimal imports = this.calculateProductGoodsTax(product);
            BigDecimal taxedPrice = product.getPrice().add(goods).add(imports);
            TaxedProduct taxedProduct = new TaxedProduct(product, taxedPrice, goods, imports);
            taxedProducts.add(taxedProduct);
        }
        return taxedProducts;
    }

    /**
     * Gets total taxes from a list of taxed products.
     *
     * @param taxedProducts {@link List<TaxedProduct>} the taxed products
     * @return the total taxes
     */
    public BigDecimal getTotalTaxes(List<TaxedProduct> taxedProducts) {
        BigDecimal totalTaxes = bigDecimal("0.00");
        for (Product product : taxedProducts) {
            BigDecimal productTotalTax = this.getTotalTaxesFromProduct(product);
            totalTaxes = totalTaxes.add(productTotalTax);
        }
        return totalTaxes;
    }

    /**
     * Gets total price with taxes from a list of taxed Products.
     *
     * @param taxedProducts {@link List<TaxedProduct>} the taxed products
     * @return the total price with taxes
     */
    public BigDecimal getTotalPriceWithTaxes(List<TaxedProduct> taxedProducts) {
        BigDecimal taxedTotalPrice = bigDecimal("0.00");
        for (TaxedProduct taxedProduct : taxedProducts) {
            taxedTotalPrice = taxedTotalPrice.add(taxedProduct.getTaxedPrice());
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
