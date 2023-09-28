package elements;

public class SellingOrder extends Order implements Comparable<SellingOrder> {
    public SellingOrder(int traderID, double amount, double price) {
        super(traderID, amount, price);
    }

    @Override
    public int compareTo(SellingOrder o) {
        // Compare based on price, amount, and traderID in ascending, descending, and ascending order respectively
        if (this.getPrice() < o.getPrice())
            return -1;
        else if (this.getPrice() > o.getPrice())
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

}