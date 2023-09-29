package elements;

public class BuyingOrder extends Order implements Comparable<BuyingOrder> {
    private double blockedUSD;
    public BuyingOrder(int traderID, double amount, double price) {
        super(traderID, amount, price);
        this.blockedUSD = 0;
    }

    public double getBlockedUSD() {
        return blockedUSD;
    }

    public void setBlockedUSD(double blockedUSD) {
        this.blockedUSD = blockedUSD;
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

	public int getOrderID() {
		return 0;
	}

    // Getters and Setters
    public int getTraderID() {
        return super.getTraderID();
    }

    public double getAmount() {
        return super.getAmount();
    }

    public double getPrice() {
        return super.getPrice();
    }

    public void setTraderID(int traderID) {
        super.setTraderID(traderID);
    }

    public void setAmount(double amount) {
        super.setAmount(amount);
    }

    public void setPrice(double price) {
        super.setPrice(price);
    }

    public Object getTrader() {
        return null;
    }
}