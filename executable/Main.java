package executable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import elements.Market;
import elements.Trader;

public class Main {
    public static Random myRandom;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the seed for the random object: ");
        long seed = scanner.nextLong();
        myRandom = new Random(seed);

        System.out.print("Enter the initial transaction fee, number of users, and number of queries (separated by spaces): ");
        int fee = scanner.nextInt();
        int numUsers = scanner.nextInt();
        int numQueries = scanner.nextInt();

        Market market = new Market(fee);

        ArrayList<Trader> traders = new ArrayList<>();
        for (int i = 0; i < numUsers; i++) {
            traders.add(new Trader(0.0, 0.0));
        }

        for (int i = 0; i < numQueries; i++) {
            System.out.println("Enter query " + (i + 1) + " (query type, trader ID, amount, price): ");
            int queryType = scanner.nextInt();
            int traderID = scanner.nextInt();
            double amount = scanner.nextDouble();
            double price = scanner.nextDouble();

            if (traderID >= 0 && traderID < traders.size()) {
                Trader trader = traders.get(traderID);
                if (queryType == 10) {
                    trader.buy(amount, price, market);
                } else if (queryType == 11) {
                    trader.buy(amount, market.getLowestSellingPrice(), market);
                } else if (queryType == 20) {
                    trader.sell(amount, price, market);
                } else if (queryType == 21) {
                    trader.sell(amount, market.getHighestBuyingPrice(), market);
                } else if (queryType == 3) {
                    trader.depositUSD(amount);
                } else if (queryType == 4) {
                    trader.withdrawUSD(amount);
                } else if (queryType == 5) {
                    trader.printWalletStatus();
                } else if (queryType == 777) {
                    distributeRewards(traders);
                } else if (queryType == 666) {
                    market.makeOpenMarketOperation(price);
                } else if (queryType == 500) {
                    market.printMarketSize();
                } else if (queryType == 501) {
                    market.printNumSuccessfulTransactions();
                } else if (queryType == 502) {
                    market.printNumInvalidQueries();
                } else if (queryType == 505) {
                    market.printCurrentPrices();
                } else if (queryType == 555) {
                    printAllWalletStatus(traders);
                } else {
                    System.out.println("Invalid query type: " + queryType);
                }
            } else {
                System.out.println("Invalid trader ID: " + traderID);
            }
        }

        scanner.close();

        System.out.println("Processing completed successfully.");
    }

    private static void distributeRewards(ArrayList<Trader> traders) {
        for (Trader trader : traders) {
            double reward = myRandom.nextDouble() * 10;
            trader.depositGoldCoin(reward);
        }
    }

    private static void printAllWalletStatus(ArrayList<Trader> traders) {
        for (Trader trader : traders) {
            trader.printWalletStatus();
        }
    }
}