package core.business.checkout;

import core.models.BuyOrder;
import core.models.Product;
import core.models.TaxedProduct;
import core.models.enums.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static utils.MathUtils.bigDecimal;

public class TaxesCalculatorTest {

    TaxesCalculator taxesCalculator;

    public TaxesCalculatorTest() {
        this.taxesCalculator = new TaxesCalculator();
    }

    @Test
    void testGetTaxedProducts_FromFirstBasket() {
        List<BuyOrder> basket = this.getFirstBasket();
        HashMap<TaxedProduct, BigInteger> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxedTotalPrice = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("29.83"), taxedTotalPrice);
    }

    @Test
    void testGetTaxedProducts_FromSecondBasket() {
        List<BuyOrder> basket = this.getSecondBasket();
        HashMap<TaxedProduct, BigInteger> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxedTotalPrice = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("65.15"), taxedTotalPrice);
    }

    @Test
    void testGetTaxedProducts_FromThirdBasket() {
        List<BuyOrder> basket =  this.getThirdBasket();
        HashMap<TaxedProduct, BigInteger> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxedTotalPrice = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("74.68"), taxedTotalPrice);
    }

    @Test
    void testGetTaxedProducts_FromFourthBasket() {
        List<BuyOrder> basket = this.getFourthBasket();
        HashMap<TaxedProduct, BigInteger> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxedTotalPrice = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("130.30"), taxedTotalPrice);
    }

    @Test
    void testGetTotalTaxes_FromFirstBasket() {
        List<BuyOrder> basket = this.getFirstBasket();
        HashMap<TaxedProduct, BigInteger> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("1.50"), taxes);
    }

    @Test
    void testGetTotalTaxes_FromSecondBasket() {
        List<BuyOrder> basket = this.getSecondBasket();
        HashMap<TaxedProduct, BigInteger> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("7.65"), taxes);
    }

    @Test
    void testGetTotalTaxes_FromThirdBasket() {
        List<BuyOrder> basket = this.getThirdBasket();
        HashMap<TaxedProduct, BigInteger> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("6.70"), taxes);
    }

    @Test
    void testGetTotalTaxes_FromFourthBasket() {
        List<BuyOrder> basket = this.getFourthBasket();
        HashMap<TaxedProduct, BigInteger> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("15.30"), taxes);
    }

    private List<BuyOrder> getFirstBasket() {
        BigInteger quantity1 = new BigInteger("1");
        Product product1 = new Product("Chocolate Bar", bigDecimal("0.85"), false, ProductType.FOOD);
        Product product2 = new Product("Book", bigDecimal("12.49"), false, ProductType.BOOK);
        Product product3 = new Product("music CD", bigDecimal("14.99"), false, ProductType.OTHER);
        BuyOrder bo1 = new BuyOrder(product1, quantity1);
        BuyOrder bo2 = new BuyOrder(product2, quantity1);
        BuyOrder bo3 = new BuyOrder(product3, quantity1);
        List<BuyOrder> buyOrders = Arrays.asList(bo1, bo2, bo3);
        return new ArrayList<>(buyOrders);
    }

    private List<BuyOrder> getSecondBasket() {
        BigInteger quantity1 = new BigInteger("1");
        Product product1 = new Product("Chocolate Box", bigDecimal("10.00"), true, ProductType.FOOD);
        Product product2 = new Product("Perfume", bigDecimal("47.50"), true, ProductType.OTHER);
        BuyOrder bo1 = new BuyOrder(product1, quantity1);
        BuyOrder bo2 = new BuyOrder(product2, quantity1);
        List<BuyOrder> buyOrders = Arrays.asList(bo1, bo2);
        return new ArrayList<>(buyOrders);
    }

    private List<BuyOrder> getThirdBasket() {
        BigInteger quantity1 = new BigInteger("1");
        Product product1 = new Product("perfume", bigDecimal("27.99"), true, ProductType.OTHER);
        Product product2 = new Product("perfume", bigDecimal("18.99"), false, ProductType.OTHER);
        Product product3 = new Product("headache pills", bigDecimal("9.75"), false, ProductType.MEDICINE);
        Product product4 = new Product("Chocolate Box", bigDecimal("11.25"), true, ProductType.FOOD);
        BuyOrder bo1 = new BuyOrder(product1, quantity1);
        BuyOrder bo2 = new BuyOrder(product2, quantity1);
        BuyOrder bo3 = new BuyOrder(product3, quantity1);
        BuyOrder bo4 = new BuyOrder(product4, quantity1);

        List<BuyOrder> buyOrders = Arrays.asList(bo1, bo2, bo3, bo4);
        return new ArrayList<>(buyOrders);
    }

    private List<BuyOrder> getFourthBasket() {
        Product product1 = new Product("Chocolate Box", bigDecimal("10.00"), true, ProductType.FOOD);
        Product product2 = new Product("Perfume", bigDecimal("47.50"), true, ProductType.OTHER);
        BuyOrder bo1 = new BuyOrder(product1, new BigInteger("2"));
        BuyOrder bo2 = new BuyOrder(product2, new BigInteger("2"));
        List<BuyOrder> buyOrders = Arrays.asList(bo1, bo2);
        return new ArrayList<>(buyOrders);
    }
}
