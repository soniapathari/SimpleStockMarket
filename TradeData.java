package main.com.java.TechnicalCodingTest;

    /*
        iii. Record a trade, with timestamp, quantity, buy or sell indicator and price
        iv.  Calculate Volume Weighted Stock Price based on trades in past 5 minutes
        (b)  Calculate the GBCE All Share Index using the geometric mean of the Volume Weighted Stock Price for all
             stocks
     */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Trade{
    //Record a trade, with timestamp, quantity, buy or sell indicator and price
    Date timestamp;
    int quantity;
    String indicator;
    double price;

    public Trade(Date timestamp, int quantity,String indicator,double price){
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.indicator = indicator;
        this.price = price;
    }
}

class Stocks{
    // store stocks with all trades details.
    String stock;
    ArrayList<Trade> trades ;

    public Stocks(String stock){
        this.stock = stock;
        this.trades = new ArrayList<>();
    }

    public  void insertRecords(Trade trade){
        this.trades.add(trade);
/*        System.out.println("Trade recode:"+
                "Stock " + this.stock +
                "timestamp" + trade.timestamp +
                "quantity"+ trade.quantity +
                "indicator" + trade.indicator +
                        "Price" +trade.price
        );*/

    }
}


public class TradeData {
    public static void main(String[] args) {

        List<Stocks> stocksList = new ArrayList<>();
        Stocks s1 = new Stocks("TEA");
        Stocks s2 = new Stocks("POP");
        Stocks s3 = new Stocks("ALE");
        Stocks s4 = new Stocks("GIN");
        Stocks s5 = new Stocks("JOE");

        Trade t1 = new Trade(new Date(),10,"buy",100.10);
        Trade t2 = new Trade(new Date(),5,"buy",40.10);
        Trade t3 = new Trade(new Date(),2,"sell",200.50);
        Trade t4 = new Trade(new Date(),14,"buy",1000.10);

        // Inserting all the trade details with stocks.
        s1.insertRecords(t1);
        s1.insertRecords(t2);
        stocksList.add(s1);

        s2.insertRecords(t2);
        s2.insertRecords(t3);
        stocksList.add(s2);

        s3.insertRecords(t1);
        s3.insertRecords(t3);
        stocksList.add(s3);

        s4.insertRecords(t4);
        s4.insertRecords(t1);
        stocksList.add(s4);

        s5.insertRecords(t4);
        s5.insertRecords(t3);
        s5.insertRecords(t2);
        stocksList.add(s5);

        double gBCEAllStockIndex =0;
        double multiplyOfVwSPrice = 1.0;
        int count =0;
        for(Stocks s :stocksList){
            double vWSPrice = calsVWSP(s.trades);
            if(vWSPrice>0){
                multiplyOfVwSPrice *= vWSPrice;
                count++;
            }
        }
        gBCEAllStockIndex = Math.pow(multiplyOfVwSPrice,1.0/count);
        System.out.println("gBCEAllStockIndex " + gBCEAllStockIndex);
    }


    //Calculating Volume Weighted Stock Price based on trades in past 5 minutes
    private static double calsVWSP(ArrayList<Trade> trades) {
        double totalTradePrice = 0.0;
        double totalQuantity =0.0;
        int quantityCount = 0;
        double volumeWeightedStockPrice=0;
        for(Trade t :trades){
            long currentTime = System.currentTimeMillis();
            long time = t.timestamp.getTime();

            if((currentTime - time)<= 300000){
                totalTradePrice += t.price;
                totalQuantity += t.quantity;
                quantityCount++;
            }
        }
        volumeWeightedStockPrice = (totalTradePrice*quantityCount)/totalQuantity;
        return volumeWeightedStockPrice;

    }


}
