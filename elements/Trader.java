package elements;
public class Trader {
    private int id;
    private Wallet wallet;
    public static int numberOfUsers = 0;

    public Trader(double USD, double GoldCoin) {
        this.id = numberOfUsers++;
        this.wallet = new Wallet(USD, GoldCoin);
    }

    public int sell(double amount, double price, Market market) {
        // Implementation of selling order by the trader
        return 0;
    }

    public int buy(double amount, double price, Market market) {
        // Implementation of buying order by the trader
        return 0;
    }
}
