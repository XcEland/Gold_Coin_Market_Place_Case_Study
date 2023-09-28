package elements;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Market {
    private PriorityQueue<SellingOrder> sellingOrders;
    private PriorityQueue<BuyingOrder> buyingOrders;
    private ArrayList<Transaction> transactions;
    private int fee;

    public Market(int fee) {
        this.fee = fee;
        sellingOrders = new PriorityQueue<>();
        buyingOrders = new PriorityQueue<>();
        transactions = new ArrayList<>();
    }

    public void giveSellOrder(SellingOrder order) {
        sellingOrders.offer(order);
        checkTransactions();
    }

    public void giveBuyOrder(BuyingOrder order) {
        buyingOrders.offer(order);
        checkTransactions();
    }

    public void makeOpenMarketOperation(double price) {
        // Implementation of open market operation
    }

     private void checkTransactions() {
        // Implementation to check for overlapping prices and make transactions
    }
}

