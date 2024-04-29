package edu.canisius.csc213.project2.quotes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StockQuote {

    private String symbol;
    private double closePrice;
    private double highestPrice;
    private double lowestPrice;
    private int numTransactions;
    private double openPrice;
    private long timeStamp;
    private double tradingVolume;

    public StockQuote(String string, double d, double e, double f, int i, double g, long l, double h) {
        this.symbol = string;
        this.closePrice = d;
        this.highestPrice = e;
        this.lowestPrice = f;
        this.numTransactions = i;
        this.openPrice = g;
        this.timeStamp = l;
        this.tradingVolume = h;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public int getNumberOfTransactions() {
        return numTransactions;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public long getTimestamp() {
        return timeStamp;
    }

    public double getTradingVolume() {
        return tradingVolume;
    }

    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("Symbol: ").append(symbol).append("\n");
        sb.append("Close Price: ").append(closePrice).append("\n");
        sb.append("Highest Price: ").append(highestPrice).append("\n");
        sb.append("Lowest Price: ").append(lowestPrice).append("\n");
        sb.append("Number of Transactions: ").append(numTransactions).append("\n");
        sb.append("Open Price: ").append(openPrice).append("\n");
        // Format the timestamp
        // SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        // String formattedDate = sdf.format(new Date(timeStamp));
        // sb.append("Timestamp: ").append(formattedDate).append("\n");
        sb.append("Trading Volume: ").append(tradingVolume).append("\n");

        return sb.toString();
    }

}
