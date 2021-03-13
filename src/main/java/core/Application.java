package core;

import core.models.Product;
import core.models.enums.ProductType;

public class Application {

    /* Program Entry point */
    public static void main(String[] args) {
        Product product1 = new Product("Chocolate Bar", 0.85, false, ProductType.FOOD);
        Product product2 = new Product("Book", 12.49, false, ProductType.BOOK);
        Product product3 = new Product("music CD", 14.99, false, ProductType.OTHER);
    }
}
