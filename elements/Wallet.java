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

    /**
     * @return double return the USD
     */
    public double getUSD() {
        return USD;
    }

    /**
     * @param USD the USD to set
     */
    public void setUSD(double USD) {
        this.USD = USD;
    }

    /**
     * @return double return the GoldCoin
     */
    public double getGoldCoin() {
        return GoldCoin;
    }

    /**
     * @param GoldCoin the GoldCoin to set
     */
    public void setGoldCoin(double GoldCoin) {
        this.GoldCoin = GoldCoin;
    }

    /**
     * @return double return the blockedUSD
     */
    public double getBlockedUSD() {
        return blockedUSD;
    }

    /**
     * @param blockedUSD the blockedUSD to set
     */
    public void setBlockedUSD(double blockedUSD) {
        this.blockedUSD = blockedUSD;
    }

    /**
     * @return double return the blockedGoldCoin
     */
    public double getBlockedGoldCoin() {
        return blockedGoldCoin;
    }

    /**
     * @param blockedGoldCoin the blockedGoldCoin to set
     */
    public void setBlockedGoldCoin(double blockedGoldCoin) {
        this.blockedGoldCoin = blockedGoldCoin;
    }

    public void addGoldCoin(double transactionAmount) {
    }

    public void removeBlockedGoldCoin(double transactionAmount) {
    }

    public void addUSD(double d) {
    }

    public void removeBlockedUSD(double transactionCost) {
    }

}
