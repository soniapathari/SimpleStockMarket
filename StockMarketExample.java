package main.com.java.TechnicalCodingTest;

    /*
        i. Given any price as input, calculate the dividend yield
        ii. Given any price as input, calculate the P/E Ratio
     */

import java.util.ArrayList;
import java.util.Scanner;

public class StockMarketExample {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        /*
        Storing all tha constant values in below elements as we have many values.
        or we can ask input from User.
         */
        String[] symbols = {"TEA","POP","ALE","GIN","JOE"};
        String[] type ={"Common","Common","Common","Preferred","Common"};
        int[] lastDividend = {0,8,23,8,13};
        String[] fixedDividendValue = {"","","","2%",""};
        int[]  perValue = {100,100,60,100,250};
        System.out.println("Enter the value of Price:");
        double price = sc.nextDouble();

        ArrayList<Double> fixedDividend = new ArrayList<>();
        ArrayList<Double> dividendYield = new ArrayList<>();
        ArrayList<Double> pERatio = new ArrayList<>();

        for(int i = 0 ;i< symbols.length;i++){
            System.out.printf("Symbols: %s, Type: %s,LastDividend: %d,FixedDividend:%s,PerValue: %d\n",
                    symbols[i] , type[i],lastDividend[i],fixedDividendValue[i],perValue[i]);
            //Check the value for Fixed Dividend if it has % value and convert into decimal.
            if(fixedDividendValue[i].contains("%")){
                fixedDividendValue[i] = fixedDividendValue[i].replace("%","");
                fixedDividend.add(i, (Double.parseDouble(fixedDividendValue[i]) / 100));
            }
           else fixedDividend.add(i, 0.0);

           //Check the Stock Type and Calculating Dividend Yield based on Stock type.
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
    }
    private static double CalPERation(double price, int i) {
          return (price / i);

    }
}
