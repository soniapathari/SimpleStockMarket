package main.com.java.TechnicalCodingTest;

public class Beverages {
    public String symbols;
    public String type;
    public int lastDividend;
    public String fixedDividendValue;
    public int perValue;

    public Beverages(String symbols,String type,int lastDividend,String fixedDividendValue,int perValue){
        this.symbols = symbols;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividendValue = fixedDividendValue;
        this.perValue =perValue;

    }

    public String getSymbols() {
        return symbols;
    }

    public String getType() {
        return type;
    }

    public int getLastDividend() {
        return lastDividend;
    }

    public String getFixedDividendValue() {
        return fixedDividendValue;
    }

    public int getPerValue() {
        return perValue;
    }

/*   public String toString(){
        return "Beverages {" +
                "symbols: " + symbols +
                ",type: " + type + ",lastDividend: " + lastDividend + ",fixedDividendValue: " + fixedDividendValue +
              ",perValue: "+ perValue+ '}';
   }*/

}
