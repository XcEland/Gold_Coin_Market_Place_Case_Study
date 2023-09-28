import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
            String line;

            // Read random seed
            line = reader.readLine();
            long seed = Long.parseLong(line);
            myRandom = new Random(seed);

            // Read initial transaction fee, number of users, and number of queries
            line = reader.readLine();
            String[] params = line.split(" ");
            int fee = Integer.parseInt(params[0]);
            int numUsers = Integer.parseInt(params[1]);
            int numQueries = Integer.parseInt(params[2]);

            // Create market
            Market market = new Market(fee);

            // Create traders
            ArrayList<Trader> traders = new ArrayList<>();
            for (int i = 0; i < numUsers; i++) {
                traders.add(new Trader(0.0, 0.0));
            }

            // Process queries
            for (int i = 0; i < numQueries; i++) {
                line = reader.readLine();
                String[] query = line.split(" ");

                int queryType = Integer.parseInt(query[0]);
                int traderID = Integer.parseInt(query[1]);

                if (queryType == 0) {
                    // Buy order
                    double amount = Double.parseDouble(query[2]);
                    double price = Double.parseDouble(query[3]);
                    traders.get(traderID).buy(amount, price, market);
                } else if (queryType == 1) {
                    // Sell order
                    double amount = Double.parseDouble(query[2]);
                    double price = Double.parseDouble(query[3]);
                    traders.get(traderID).sell(amount, price, market);
                } else if (queryType == 666) {
                    // Open market operation
                    double price = Double.parseDouble(query[2]);
                    market.makeOpenMarketOperation(price);
                }
            }

            // Close the reader and writer
            reader.close();
            writer.close();

            System.out.println("Processing completed successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("The input file was not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading/writing the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in the input file: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input format in the input file: " + e.getMessage());
        }
    }
}