package elements;

public class BuyingOrder extends Order implements Comparable<BuyingOrder> {
    public BuyingOrder(int traderID, double amount, double price) {
        super(traderID, amount, price);
    }

    @Override
    public int compareTo(BuyingOrder o) {
        // Compare based on price, amount, and traderID in descending, descending, and ascending order respectively
        if (this.getPrice() > o.getPrice())
            return -1;
        else if (this.getPrice() < o.getPrice())
            return 1;
        else {
            if (this.getAmount() > o.getAmount())
                return -1;
            else if (this.getAmount() < o.getAmount())
                return 1;
            else
                return Integer.compare(this.getTraderID(), o.getTraderID());
        }
    }

    // Getters and Setters
}