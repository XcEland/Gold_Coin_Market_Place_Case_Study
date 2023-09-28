package elements;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Market {
    private PriorityQueue<SellingOrder> sellingOrders;
    private PriorityQueue<BuyingOrder> buyingOrders;
    private ArrayList<Transaction> transactions;
    private double fee;

    public Market(double fee) {
        this.fee = fee;
        this.sellingOrders = new PriorityQueue<>();
        this.buyingOrders = new PriorityQueue<>();
        this.transactions = new ArrayList<>();
    }

    public void addSellingOrder(SellingOrder order) {
        sellingOrders.offer(order);
        checkTransactions();
    }

    public void addBuyingOrder(BuyingOrder order) {
        buyingOrders.offer(order);
        checkTransactions();
    }

    public void makeOpenMarketOperation(double price) {
        // Sell orders with prices lower than or equal to the open market price are executed
        while (!sellingOrders.isEmpty() && sellingOrders.peek().getPrice() <= price) {
            SellingOrder sellingOrder = sellingOrders.poll();
            double amount = sellingOrder.getAmount();
            int sellerID = sellingOrder.getTraderID();

            // Check if there are any corresponding buying orders
            while (!buyingOrders.isEmpty() && amount > 0) {
                BuyingOrder buyingOrder = buyingOrders.peek();
                if (buyingOrder.getPrice() >= price && buyingOrder.getAmount() >= amount) {
                    // Execute a transaction
                    double transactionPrice = price;
                    double transactionAmount = amount;
                    int buyerID = buyingOrder.getTraderID();

                    // Update the amounts in the buying and selling orders
                    buyingOrder.setAmount(buyingOrder.getAmount() - amount);
                    sellingOrder.setAmount(sellingOrder.getAmount() - amount);

                    // Create a new transaction and add it to the list
                    Transaction transaction = new Transaction(buyingOrder, sellingOrder);
                    transactions.add(transaction);

                    amount = 0; // All amount from the selling order has been fulfilled
                    if (buyingOrder.getAmount() == 0)
                        buyingOrders.poll(); // Remove the buying order if it's completely fulfilled
                } else if (buyingOrder.getPrice() >= price && buyingOrder.getAmount() < amount) {
                    // Execute a transaction with partial fulfillment
                    double transactionPrice = price;
                    double transactionAmount = buyingOrder.getAmount();
                    int buyerID = buyingOrder.getTraderID();

                    // Update the amounts in the buying and selling orders
                    amount -= buyingOrder.getAmount();
                    buyingOrders.poll(); // Remove the fulfilled buying order

                    // Create a new transaction and add it to the list
                    Transaction transaction = new Transaction(buyingOrder, sellingOrder);
                    transactions.add(transaction);
                } else {
                    break; // No more matching buying orders
                }
            }

            // Check if the selling order is partially fulfilled and add it back to the queue
            if (sellingOrder.getAmount() > 0)
                sellingOrders.offer(sellingOrder);
        }
    }

    private void checkTransactions() {
        // Loop through each buying order and selling order to check for overlapping prices and make transactions
        for (BuyingOrder buyingOrder : buyingOrders) {
            double buyPrice = buyingOrder.getPrice();
            double buyAmount = buyingOrder.getAmount();

            for (SellingOrder sellingOrder : sellingOrders) {
                double sellPrice = sellingOrder.getPrice();
                double sellAmount = sellingOrder.getAmount();

                if (sellPrice <= buyPrice && sellAmount <= buyAmount) {
                    // Execute a transaction
                    double transactionPrice = sellPrice;
                    double transactionAmount = sellAmount;
                    int buyerID = buyingOrder.getTraderID();
                    int sellerID = sellingOrder.getTraderID();

                    // Update the amounts in the buying and selling orders
                    buyAmount -= sellAmount;
                    sellAmount = 0;

                    // Create a new transaction and add it to the list
                    Transaction transaction = new Transaction(buyingOrder, sellingOrder);
                    transactions.add(transaction);

                    if (buyAmount == 0)
                        break; // The buying order is completely fulfilled, exit the loop
                }
            }
        }
    }
}