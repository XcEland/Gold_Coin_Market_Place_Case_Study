package elements;
public class SellingOrder extends Order implements Comparable<SellingOrder> {
    public SellingOrder(int traderID, double amount, double price) {
        super(traderID, amount, price);
    }

    @Override
    public int compareTo(SellingOrder o) {
        // Implementation of comparison logic for SellingOrder
        return 0;
    }
}

