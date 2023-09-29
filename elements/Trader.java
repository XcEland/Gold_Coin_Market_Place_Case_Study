package elements;

import java.util.Random;
public class Trader {
    private int id;
    private Wallet wallet;
    public static int numberOfUsers = 0;

    public Trader(double USD, double GoldCoin) {
        this.id = numberOfUsers++;
        this.wallet = new Wallet(USD, GoldCoin);
    }

    public int sell(double amount, double price, Market market) {
        // Create a new SellingOrder with the trader's ID, amount, and price
        SellingOrder sellingOrder = new SellingOrder(this.id, amount, price);

        // Add the selling order to the market
        market.addSellingOrder(sellingOrder);

        // Return the ID of the added selling order
        return sellingOrder.getOrderID();
    }

    public int buy(double amount, double price, Market market) {
        // Create a new BuyingOrder with the trader's ID, amount, and price
        BuyingOrder buyingOrder = new BuyingOrder(this.id, amount, price);

        // Add the buying order to the market
        market.addBuyingOrder(buyingOrder);

        // Return the ID of the added buying order
        return buyingOrder.getOrderID();
    }
    
    public Wallet getWallet() {
        return wallet;
    }
}
