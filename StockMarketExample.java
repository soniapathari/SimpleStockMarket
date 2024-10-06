package main.com.java.TechnicalCodingTest;

    /*
        i. Given any price as input, calculate the dividend yield
        ii. Given any price as input, calculate the P/E Ratio
     */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockMarketExample {

    public static void main(String[] args) {

        File csvFile = new File( "src/main/com/java/TechnicalCodingTest/Sample.csv");
        String data;
        List<Beverages> beverages = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of Price:");
        double price = sc.nextDouble();

        ArrayList<Double> fixedDividend = new ArrayList<>();
        ArrayList<Double> dividendYield = new ArrayList<>();
        ArrayList<Double> pERatio = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((data = br.readLine()) != null) {
                String[] marketData = data.split(",");
                String symbol = marketData[0];
                String types = marketData[1];
                int lastDividends = Integer.parseInt(marketData[2]);
                String fixedDividendValues = marketData[3];
                int perValues = Integer.parseInt(marketData[4]);

                Beverages beverage = new Beverages(symbol, types, lastDividends, fixedDividendValues, perValues);
                beverages.add(beverage);

            }
            String[] symbols = beverages.stream().map(Beverages::getSymbols).toArray(String[]::new);
            String[] type = beverages.stream().map(Beverages::getType).toArray(String[]::new);
            int[] lastDividend = beverages.stream().mapToInt(Beverages::getLastDividend).toArray();
            String[] fixedDividendValue = beverages.stream().map(Beverages::getFixedDividendValue).toArray(String[]::new);
            int[] perValue = beverages.stream().mapToInt(Beverages::getPerValue).toArray();


            for(int i = 0 ;i< symbols.length;i++){
                System.out.printf("Symbols: %s, Type: %s,LastDividend: %d,FixedDividend:%s,PerValue: %d\n",
                         symbols[i] , type[i],lastDividend[i],fixedDividendValue[i],perValue[i]);
                //Check the value for Fixed Dividend if it has % value and convert into decimal.
                if(fixedDividendValue[i].contains("%")){
                    fixedDividendValue[i] = fixedDividendValue[i].replace("%","");
                    fixedDividend.add(i, (Double.parseDouble(fixedDividendValue[i]) / 100));
                    }
                else fixedDividend.add(i, 0.0);

                //Check the Stock Type and Calculating Dividend Yield.
                if(type[i].equals("Common")){
                    dividendYield.add(i, lastDividend[i] / price);
                    }
                else if(type[i].equals("Preferred")){
                    dividendYield.add(i, (fixedDividend.get(i) * perValue[i]) / price);
                    }
                //Calculate P/E Ratio -
                pERatio.add(i, CalPERation(price, lastDividend[i]));

            }
            // Printing values for Dividend Yield and P/E Ratio.
            for(double d : dividendYield){
                System.out.println("Value of Dividend Yield are : " + d);
            }

            for(double e : pERatio){
                System.out.println("Value of P/E Ratio are : "  + e);
            }

        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
    private static double CalPERation(double price, int i) {
          return (price / i);
    }
}
