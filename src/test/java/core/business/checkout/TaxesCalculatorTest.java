package core.business.checkout;

import core.models.Product;
import core.models.TaxedProduct;
import core.models.enums.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.MathUtils.bigDecimal;

public class TaxesCalculatorTest {

    TaxesCalculator taxesCalculator;

    public TaxesCalculatorTest() {
        this.taxesCalculator = new TaxesCalculator();
    }

    @Test
    void testGetTaxedProducts_FromFirstBasket() {
        List<Product> basket = this.getFirstBasketInput();
        List<TaxedProduct> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxedTotalPrice = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("29.83"), taxedTotalPrice);
    }

    @Test
    void testGetTaxedProducts_FromSecondBasket() {
        List<Product> basket = this.getSecondBasketInput();
        List<TaxedProduct> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxedTotalPrice = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("65.15"), taxedTotalPrice);
    }

    @Test
    void testGetTaxedProducts_FromThirdBasket() {
        List<Product> basket = this.getThirdBasketInput();
        List<TaxedProduct> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxedTotalPrice = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("74.68"), taxedTotalPrice);
    }

    @Test
    void testGetTotalTaxes_FromFirstBasket() {
        List<Product> basket = this.getFirstBasketInput();
        List<TaxedProduct> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("1.50"), taxes);
    }

    @Test
    void testGetTotalTaxes_FromSecondBasket() {
        List<Product> basket = this.getSecondBasketInput();
        List<TaxedProduct> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("7.65"), taxes);
    }

    @Test
    void testGetTotalTaxes_FromThirdBasket() {
        List<Product> basket = this.getThirdBasketInput();
        List<TaxedProduct> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("6.70"), taxes);
    }

    private List<Product> getFirstBasketInput() {
        Product product1 = new Product("Chocolate Bar", bigDecimal("0.85"), false, ProductType.FOOD);
        Product product2 = new Product("Book", bigDecimal("12.49"), false, ProductType.BOOK);
        Product product3 = new Product("music CD", bigDecimal("14.99"), false, ProductType.OTHER);
        List<Product> products = Arrays.asList(product1, product2, product3);
        return new ArrayList<>(products);
    }

    private List<Product> getSecondBasketInput() {
        Product product1 = new Product("Chocolate Box", bigDecimal("10.00"), true, ProductType.FOOD);
        Product product2 = new Product("Perfume", bigDecimal("47.50"), true, ProductType.OTHER);
        List<Product> products = Arrays.asList(product1, product2);
        return new ArrayList<>(products);
    }

    private List<Product> getThirdBasketInput() {
        Product product1 = new Product("perfume", bigDecimal("27.99"), true, ProductType.OTHER);
        Product product2 = new Product("perfume", bigDecimal("18.99"), false, ProductType.OTHER);
        Product product3 = new Product("headache pills", bigDecimal("9.75"), false, ProductType.MEDICINE);
        Product product4 = new Product("Chocolate Box", bigDecimal("11.25"), true, ProductType.FOOD);
        List<Product> products = Arrays.asList(product1, product2, product3, product4);
        return new ArrayList<>(products);
    }
}
