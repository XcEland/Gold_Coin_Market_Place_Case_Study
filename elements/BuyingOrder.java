package elements;
public class BuyingOrder extends Order implements Comparable<BuyingOrder> {
    public BuyingOrder(int traderID, double amount, double price) {
        super(traderID, amount, price);
    }

    @Override
    public int compareTo(BuyingOrder o) {
        // Implementation of comparison logic for BuyingOrder
        return 0;
    }
}