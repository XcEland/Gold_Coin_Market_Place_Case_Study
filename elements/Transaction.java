package elements;
public class Transaction {
    private BuyingOrder buyingOrder;
    private SellingOrder sellingOrder;

    public Transaction(BuyingOrder buyingOrder, SellingOrder sellingOrder) {
        this.buyingOrder = buyingOrder;
        this.sellingOrder = sellingOrder;
    }

    public BuyingOrder getBuyingOrder() {
        return buyingOrder;
    }

    public SellingOrder getSellingOrder() {
        return sellingOrder;
    }
}
