package elements;
public class Wallet {
    private double USD;
    private double GoldCoin;
    private double blockedUSD;
    private double blockedGoldCoin;

    public Wallet(double USD, double GoldCoin) {
        this.USD = USD;
        this.GoldCoin = GoldCoin;
        this.blockedUSD = 0.0;
        this.blockedGoldCoin = 0.0;
    }
}
