package core.business;

import core.business.checkout.TaxesCalculator;
import core.dto.BuyOrder;
import core.dto.Receipt;
import core.dto.TaxedOrder;

import java.math.BigDecimal;
import java.util.List;

public class Checkout {

    private TaxesCalculator taxesCalculator;

    public Checkout() {
        this.taxesCalculator = new TaxesCalculator();
    }

    /**
     * Checkouts a shopping basket for a receipt
     *
     * @param orderList the List of BuyOrder
     * @return the shopping Receipt
     */
    public Receipt checkoutShoppingBasket(List<BuyOrder> orderList) {
        List<TaxedOrder> taxedProducts = this.taxesCalculator.getTaxedProducts(orderList);
        BigDecimal allTaxes = this.taxesCalculator.getTotalTaxes(taxedProducts);
        BigDecimal totalPriceWithTaxes = this.taxesCalculator.getTotalPriceWithTaxes(taxedProducts);
        return new Receipt(taxedProducts, totalPriceWithTaxes, allTaxes);
    }

}
