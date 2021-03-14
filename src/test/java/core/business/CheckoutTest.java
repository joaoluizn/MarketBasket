package core.business;

import TestUtils.TestUtils;
import core.models.BuyOrder;
import core.models.Receipt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class CheckoutTest {

    private Checkout checkout;

    public CheckoutTest() {
        this.checkout = new Checkout();
    }


    @Test
    public void testCheckoutFirstBasket() {
        List<BuyOrder> orderList = TestUtils.getFirstBasket();
        Receipt receipt = this.checkout.checkoutShoppingBasket(orderList);
        Assertions.assertEquals(TestUtils.FIRST_BASKET_RECEIPT, receipt.toString());
    }

    @Test
    public void testCheckoutSecondBasket() {
        List<BuyOrder> orderList = TestUtils.getSecondBasket();
        Receipt receipt = this.checkout.checkoutShoppingBasket(orderList);
        Assertions.assertEquals(TestUtils.SECOND_BASKET_RECEIPT, receipt.toString());
    }

    @Test
    public void testCheckoutThirdBasket() {
        List<BuyOrder> orderList = TestUtils.getThirdBasket();
        Receipt receipt = this.checkout.checkoutShoppingBasket(orderList);
        Assertions.assertEquals(TestUtils.THIRD_BASKET_RECEIPT, receipt.toString());
    }

    @Test
    public void testCheckoutFourthBasket() {
        List<BuyOrder> orderList = TestUtils.getFourthBasket();
        Receipt receipt = this.checkout.checkoutShoppingBasket(orderList);
        Assertions.assertEquals(TestUtils.FOURTH_BASKET_RECEIPT, receipt.toString());
    }
}
