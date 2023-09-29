package executable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import elements.Market;
import elements.Trader;
public class Main {
    public static Random myRandom;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Main <input_file> <output_file>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            // Read random seed
            long seed = Long.parseLong(reader.readLine());
            myRandom = new Random(seed);

            // Read initial transaction fee, number of users, and number of queries
            String[] line = reader.readLine().split(" ");
            int fee = Integer.parseInt(line[0]);
            int numUsers = Integer.parseInt(line[1]);
            int numQueries = Integer.parseInt(line[2]);

            Market market = new Market(fee);

            // Read initial wallet balances of traders
            for (int i = 0; i < numUsers; i++) {
                line = reader.readLine().split(" ");
                double usdAmount = Double.parseDouble(line[0]);
                double goldCoinAmount = Double.parseDouble(line[1]);

                Trader trader = new Trader(usdAmount, goldCoinAmount);
                market.addTrader(trader);
            }

            // Process queries
for (int i = 0; i < numQueries; i++) {
    line = reader.readLine().split(" ");
    int queryType = Integer.parseInt(line[0]);

    switch (queryType) {
        case 10:
            // Give buying order of specific price
            int traderId = Integer.parseInt(line[1]);
            double price1 = Double.parseDouble(line[2]);
            double amount1 = Double.parseDouble(line[3]);

            Trader buyer = market.getTrader(traderId);
            if (buyer != null) {
                buyer.buy(amount1, price1, market);
            } else {
                System.out.println("Invalid query: Trader not found");
            }
            break;
        case 11:
            // Give buying order of market price
            traderId = Integer.parseInt(line[1]);
            double amount2 = Double.parseDouble(line[2]);

            Trader buyer2 = market.getTrader(traderId);
            if (buyer2 != null) {
                buyer2.buy(amount2, market.getBestSellingPrice(), market);
            } else {
                System.out.println("Invalid query: Trader not found");
            }
            break;
        case 20:
            // Give selling order of specific price
            traderId = Integer.parseInt(line[1]);
            double price2 = Double.parseDouble(line[2]);
            double amount3 = Double.parseDouble(line[3]);

            Trader seller = market.getTrader(traderId);
            if (seller != null) {
                seller.sell(amount3, price2, market);
            } else {
                System.out.println("Invalid query: Trader not found");
            }
            break;
        case 21:
            // Give selling order of market price
            traderId = Integer.parseInt(line[1]);
            double amount4 = Double.parseDouble(line[2]);

            Trader seller2 = market.getTrader(traderId);
            if (seller2 != null) {
                seller2.sell(amount4, market.getBestBuyingPrice(), market);
            } else {
                System.out.println("Invalid query: Trader not found");
            }
            break;
        case 3:
            // Deposit a certain amount of USD to wallet
            traderId = Integer.parseInt(line[1]);
            double depositAmount = Double.parseDouble(line[2]);

            Trader trader = market.getTrader(traderId);
            if (trader != null) {
                trader.getWallet().depositUSD(depositAmount);
            } else {
                System.out.println("Invalid query: Trader not found");
            }
            break;
        case 4:
            // Withdraw a certain amount of USD from wallet
            traderId = Integer.parseInt(line[1]);
            double withdrawAmount = Double.parseDouble(line[2]);

            Trader trader2 = market.getTrader(traderId);
            if (trader2 != null) {
                trader2.getWallet().withdrawUSD(withdrawAmount);
            } else {
                System.out.println("Invalid query: Trader not found");
            }
            break;
        case 5:
            // Print wallet status
            traderId = Integer.parseInt(line[1]);

            Trader trader3 = market.getTrader(traderId);
            if (trader3 != null) {
                System.out.println("Trader " + traderId + ": " + trader3.getWallet().getUSD() + "$ " + trader3.getWallet().getGoldCoin() + " GC");
            } else {
                System.out.println("Invalid query: Trader not found");
            }
            break;
        case 777:
            // Give rewards to all traders
            for (Trader t : market.getTraders()) {
                double reward = myRandom.nextDouble() * 10;
                t.getWallet().depositGoldCoin(reward);
            }
            break;
        case 666:
            // Make open market operation
            double price = Double.parseDouble(line[1]);
            market.adjustGoldCoinPrice(price);
            break;
        case 500:
            // Print the current market size
            writer.write("Current market size: " + market.getTotalBuyingPQ() + " " + market.getTotalSellingPQ());
            writer.newLine();
            break;
        case 501:
            // Print number of successful transactions
            writer.write("Number of successful transactions: " + market.getSuccessfulTransactions());
            writer.newLine();
            break;
        case 502:
            // Print the number of invalid queries
            writer.write("Number of invalid queries: " + market.getInvalidQueries());
            writer.newLine();
            break;
        case 505:
            // Print the current prices
            writer.write("Current prices: " + market.getBestBuyingPrice() + " " + market.getBestSellingPrice() + " " + market.getAveragePrice());
            writer.newLine();
            break;
        case 555:
            // Print all trader's wallet status
            for (Trader t : market.getTraders()) {
                writer.write(t.toString());
                writer.newLine();
            }
            break;
        default:
            System.out.println("Invalid query type: " + queryType);
            break;
    }
}

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}