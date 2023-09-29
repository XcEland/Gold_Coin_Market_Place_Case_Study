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
                if (buyingOrder.getPrice() >= price) {
                    // Calculate the transaction amount based on the minimum of buying and selling order amounts
                    double transactionAmount = Math.min(amount, buyingOrder.getAmount());

                    // Calculate the transaction price based on the selling order price
                    double transactionPrice = sellingOrder.getPrice();

                    // Calculate the transaction fee
                    double transactionFee = transactionAmount * transactionPrice * fee / 1000.0;

                    // Calculate the total transaction cost for the buyer
                    double transactionCost = transactionAmount * transactionPrice + transactionFee;

                    // Update the amounts in the buying and selling orders
                    amount -= transactionAmount;
                    buyingOrder.setAmount(buyingOrder.getAmount() - transactionAmount);

                    // Create a new transaction and add it to the list
                    Transaction transaction = new Transaction(buyingOrder, sellingOrder, transactionAmount, transactionPrice, transactionFee);
                    transactions.add(transaction);

                    // Retrieve trader and wallet objects
                    Trader buyingTrader = (Trader) buyingOrder.getTrader();
                    Wallet buyingWallet = buyingTrader.getWallet();
                    Trader sellingTrader = (Trader) sellingOrder.getTrader();
                    Wallet sellingWallet = sellingTrader.getWallet();

                    // Update the buyer's wallet
                    buyingWallet.removeBlockedUSD(transactionCost);
                    buyingWallet.addGoldCoin(transactionAmount);

                    // Update the seller's wallet
                    sellingWallet.removeBlockedGoldCoin(transactionAmount);
                    sellingWallet.addUSD(transactionAmount * transactionPrice * (1 - fee / 1000.0));

                    // Remove the buying order if it's completely fulfilled
                    if (buyingOrder.getAmount() == 0)
                        buyingOrders.poll();
                } else {
                    break; // No more matching buying orders
                }
            }

            // Check if the selling order is partially fulfilled and add it back to the queue
            if (amount > 0)
                sellingOrders.offer(new SellingOrder(sellingOrder.getTraderID(), sellingOrder.getPrice(), amount));
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

                if (sellPrice <= buyPrice) {
                    // Calculate the transaction amount based on the minimum of buying and selling order amounts
                    double transactionAmount = Math.min(sellAmount, buyAmount);

                    // Calculate the transaction price based on the selling order price
                    double transactionPrice = sellingOrder.getPrice();

                    // Calculate the transaction fee
                    double transactionFee = transactionAmount * transactionPrice * fee / 1000.0;

                    // Calculate the total transaction cost for the buyer
                    double transactionCost = transactionAmount * transactionPrice + transactionFee;

                    // Update the amounts in the buying and selling orders
                    buyAmount -= transactionAmount;
                    sellAmount -= transactionAmount;

                    // Create a new transaction and add it to the list
                    Transaction transaction = new Transaction(buyingOrder, sellingOrder, transactionAmount, transactionPrice, transactionFee);
                    transactions.add(transaction);

                    // Update the buyer's wallet
                    Trader buyingTrader = (Trader) buyingOrder.getTrader();
                    Wallet buyingWallet = buyingTrader.getWallet();

                    buyingWallet.removeBlockedUSD(transactionCost);
                    buyingWallet.addGoldCoin(transactionAmount);

                    // Update the seller's wallet
                    Trader sellingTrader = (Trader) sellingOrder.getTrader();
                    Wallet sellingWallet = sellingTrader.getWallet();

                    sellingWallet.removeBlockedGoldCoin(transactionAmount);
                    sellingWallet.addUSD(transactionAmount * transactionPrice * (1 - fee / 1000.0));

                    // The buying order is completely fulfilled, exit the loop
                    if (buyAmount == 0) {
                        break;
                    }
                }
            }
        }
    }

    public void addTrader(Trader trader) {
    }

    public Trader getTrader(int traderId) {
        return null;
    }

    public double getBestSellingPrice() {
        return 0;
    }

    public double getBestBuyingPrice() {
        return 0;
    }

    public Trader[] getTraders() {
        return null;
    }

    public void adjustGoldCoinPrice(double price) {
    }

    public String getTotalBuyingPQ() {
        return null;
    }

    public String getTotalSellingPQ() {
        return null;
    }

    public String getSuccessfulTransactions() {
        return null;
    }

    public String getInvalidQueries() {
        return null;
    }

    public String getAveragePrice() {
        return null;
    }
}