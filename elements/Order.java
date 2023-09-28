package elements;
public class Order {
    protected double amount;
    protected double price;
    protected int traderID;

    public Order(int traderID, double amount, double price) {
        this.traderID = traderID;
        this.amount = amount;
        this.price = price;
    }
}
