package elements;
public class Transaction {
    private BuyingOrder buyingOrder;
    private SellingOrder sellingOrder;
    private double amount;
    private double price;
    private double fee;

    public Transaction(BuyingOrder buyingOrder, SellingOrder sellingOrder, double amount, double price, double fee) {
        this.buyingOrder = buyingOrder;
        this.sellingOrder = sellingOrder;
        this.amount = amount;
        this.price = price;
        this.fee = fee;
    }

    public BuyingOrder getBuyingOrder() {
        return buyingOrder;
    }

    public SellingOrder getSellingOrder() {
        return sellingOrder;
    }

    public double getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public double getFee() {
        return fee;
    }
}
