package core;

import core.business.Checkout;
import core.dto.BuyOrder;
import core.models.Product;
import core.dto.Receipt;
import core.models.enums.ProductType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.MathUtils.bigDecimal;

public class Application {

    private static final String BOX_OF_CHOCOLATES = "box of chocolates";
    private static final String BOTTLE_OF_PERFUME = "bottle of perfume";
    private static final String BOOK = "book";
    private static final String MUSIC_CD = "music CD";
    private static final String CHOCOLATE_BAR = "chocolate bar";
    private static final String PACKET_HEADACHE_PILLS = "packet of headache pills";

    private static List<BuyOrder> getFirstBasket() {
        BigInteger quantity1 = new BigInteger("1");
        Product product1 = new Product(BOOK, bigDecimal("12.49"), false, ProductType.BOOK);
        Product product2 = new Product(MUSIC_CD, bigDecimal("14.99"), false, ProductType.OTHER);
        Product product3 = new Product(CHOCOLATE_BAR, bigDecimal("0.85"), false, ProductType.FOOD);
        BuyOrder bo1 = new BuyOrder(product1, quantity1);
        BuyOrder bo2 = new BuyOrder(product2, quantity1);
        BuyOrder bo3 = new BuyOrder(product3, quantity1);
        List<BuyOrder> buyOrders = Arrays.asList(bo1, bo2, bo3);
        return new ArrayList<>(buyOrders);
    }

    private static List<BuyOrder> getSecondBasket() {
        BigInteger quantity1 = new BigInteger("1");
        Product product1 = new Product(BOX_OF_CHOCOLATES, bigDecimal("10.00"), true, ProductType.FOOD);
        Product product2 = new Product(BOTTLE_OF_PERFUME, bigDecimal("47.50"), true, ProductType.OTHER);
        BuyOrder bo1 = new BuyOrder(product1, quantity1);
        BuyOrder bo2 = new BuyOrder(product2, quantity1);
        List<BuyOrder> buyOrders = Arrays.asList(bo1, bo2);
        return new ArrayList<>(buyOrders);
    }

    private static List<BuyOrder> getThirdBasket() {
        BigInteger quantity1 = new BigInteger("1");
        Product product1 = new Product(BOTTLE_OF_PERFUME, bigDecimal("27.99"), true, ProductType.OTHER);
        Product product2 = new Product(BOTTLE_OF_PERFUME, bigDecimal("18.99"), false, ProductType.OTHER);
        Product product3 = new Product(PACKET_HEADACHE_PILLS, bigDecimal("9.75"), false, ProductType.MEDICINE);
        Product product4 = new Product(BOX_OF_CHOCOLATES, bigDecimal("11.25"), true, ProductType.FOOD);
        BuyOrder bo1 = new BuyOrder(product1, quantity1);
        BuyOrder bo2 = new BuyOrder(product2, quantity1);
        BuyOrder bo3 = new BuyOrder(product3, quantity1);
        BuyOrder bo4 = new BuyOrder(product4, quantity1);
        List<BuyOrder> buyOrders = Arrays.asList(bo1, bo2, bo3, bo4);
        return new ArrayList<>(buyOrders);
    }

    /* Program Entry point */
    public static void main(String[] args) {
        Checkout checkout = new Checkout();

        List<BuyOrder> basketOne = getFirstBasket();
        List<BuyOrder> basketTwo = getSecondBasket();
        List<BuyOrder> basketThree = getThirdBasket();

        Receipt receiptOne = checkout.checkoutShoppingBasket(basketOne);
        Receipt receiptTwo = checkout.checkoutShoppingBasket(basketTwo);
        Receipt receiptThree = checkout.checkoutShoppingBasket(basketThree);

        System.out.println(receiptOne);
        System.out.println(receiptTwo);
        System.out.println(receiptThree);
    }
}
