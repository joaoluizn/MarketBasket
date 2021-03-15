package TestUtils;

import core.dto.BuyOrder;
import core.models.Product;
import core.models.enums.ProductType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.MathUtils.bigDecimal;

public class TestUtils {

    private static final String BOX_OF_CHOCOLATES = "box of chocolates";
    private static final String BOTTLE_OF_PERFUME = "bottle of perfume";
    private static final String BOOK = "book";
    private static final String MUSIC_CD = "music CD";
    private static final String CHOCOLATE_BAR = "chocolate bar";
    private static final String PACKET_HEADACHE_PILLS = "packet of headache pills";

    public static final String FIRST_BASKET_RECEIPT = "1 book: 12.49\n" +
            "1 music CD: 16.49\n" +
            "1 chocolate bar: 0.85\n" +
            "Sales Taxes: 1.50\n" +
            "Total: 29.83\n";


    public static final String SECOND_BASKET_RECEIPT = "1 imported box of chocolates: 10.50\n" +
            "1 imported bottle of perfume: 54.65\n" +
            "Sales Taxes: 7.65\n" +
            "Total: 65.15\n";

    public static final String THIRD_BASKET_RECEIPT = "1 imported bottle of perfume: 32.19\n" +
            "1 bottle of perfume: 20.89\n" +
            "1 packet of headache pills: 9.75\n" +
            "1 imported box of chocolates: 11.85\n" +
            "Sales Taxes: 6.70\n" +
            "Total: 74.68\n";

    public static final String FOURTH_BASKET_RECEIPT = "2 imported box of chocolates: 21.00\n" +
            "2 imported bottle of perfume: 109.30\n" +
            "Sales Taxes: 15.30\n" +
            "Total: 130.30\n";

    public static List<BuyOrder> getFirstBasket() {
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

    public static List<BuyOrder> getSecondBasket() {
        BigInteger quantity1 = new BigInteger("1");
        Product product1 = new Product(BOX_OF_CHOCOLATES, bigDecimal("10.00"), true, ProductType.FOOD);
        Product product2 = new Product(BOTTLE_OF_PERFUME, bigDecimal("47.50"), true, ProductType.OTHER);
        BuyOrder bo1 = new BuyOrder(product1, quantity1);
        BuyOrder bo2 = new BuyOrder(product2, quantity1);
        List<BuyOrder> buyOrders = Arrays.asList(bo1, bo2);
        return new ArrayList<>(buyOrders);
    }

    public static List<BuyOrder> getThirdBasket() {
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

    public static List<BuyOrder> getFourthBasket() {
        BigInteger quantity2 = new BigInteger("2");
        Product product1 = new Product(BOX_OF_CHOCOLATES, bigDecimal("10.00"), true, ProductType.FOOD);
        Product product2 = new Product(BOTTLE_OF_PERFUME, bigDecimal("47.50"), true, ProductType.OTHER);
        BuyOrder bo1 = new BuyOrder(product1, quantity2);
        BuyOrder bo2 = new BuyOrder(product2, quantity2);
        List<BuyOrder> buyOrders = Arrays.asList(bo1, bo2);
        return new ArrayList<>(buyOrders);
    }

}
