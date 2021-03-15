package core.business.checkout;

import TestUtils.TestUtils;
import core.dto.BuyOrder;
import core.dto.TaxedOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static utils.MathUtils.bigDecimal;

public class TaxesCalculatorTest {

    TaxesCalculator taxesCalculator;

    public TaxesCalculatorTest() {
        this.taxesCalculator = new TaxesCalculator();
    }

    @Test
    void testGetTaxedProducts_FromFirstBasket() {
        List<BuyOrder> basket = TestUtils.getFirstBasket();
        List<TaxedOrder> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxedTotalPrice = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("29.83"), taxedTotalPrice);
    }

    @Test
    void testGetTaxedProducts_FromSecondBasket() {
        List<BuyOrder> basket = TestUtils.getSecondBasket();
        List<TaxedOrder> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxedTotalPrice = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("65.15"), taxedTotalPrice);
    }

    @Test
    void testGetTaxedProducts_FromThirdBasket() {
        List<BuyOrder> basket = TestUtils.getThirdBasket();
        List<TaxedOrder> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxedTotalPrice = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("74.68"), taxedTotalPrice);
    }

    @Test
    void testGetTaxedProducts_FromFourthBasket() {
        List<BuyOrder> basket = TestUtils.getFourthBasket();
        List<TaxedOrder> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxedTotalPrice = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("130.30"), taxedTotalPrice);
    }

    @Test
    void testGetTotalTaxes_FromFirstBasket() {
        List<BuyOrder> basket = TestUtils.getFirstBasket();
        List<TaxedOrder> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("1.50"), taxes);
    }

    @Test
    void testGetTotalTaxes_FromSecondBasket() {
        List<BuyOrder> basket = TestUtils.getSecondBasket();
        List<TaxedOrder> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("7.65"), taxes);
    }

    @Test
    void testGetTotalTaxes_FromThirdBasket() {
        List<BuyOrder> basket = TestUtils.getThirdBasket();
        List<TaxedOrder> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("6.70"), taxes);
    }

    @Test
    void testGetTotalTaxes_FromFourthBasket() {
        List<BuyOrder> basket = TestUtils.getFourthBasket();
        List<TaxedOrder> taxedProducts = this.taxesCalculator.getTaxedProducts(basket);
        BigDecimal taxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        Assertions.assertEquals(bigDecimal("15.30"), taxes);
    }

}
