package core;

import core.models.Product;
import core.models.enums.ProductType;

import static utils.MathUtils.bigDecimal;

public class Application {

    /* Program Entry point */
    public static void main(String[] args) {
        Product product1 = new Product("Chocolate Bar", bigDecimal("0.85"), false, ProductType.FOOD);
        Product product2 = new Product("Book", bigDecimal("12.49"), false, ProductType.BOOK);
        Product product3 = new Product("music CD", bigDecimal("14.99"), false, ProductType.OTHER);
    }
}
