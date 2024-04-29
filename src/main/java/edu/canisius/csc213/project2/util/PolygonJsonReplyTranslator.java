package edu.canisius.csc213.project2.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.canisius.csc213.project2.quotes.*;


import java.io.IOException;

public class PolygonJsonReplyTranslator {

    public StockQuote translateJsonToFinancialInstrument(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        // Extracting data from JSON
        String symbol = rootNode.path("ticker").asText();
        double closePrice = rootNode.path("results").get(0).path("c").asDouble();
        double highestPrice = rootNode.path("results").get(0).path("h").asDouble();
        double lowestPrice = rootNode.path("results").get(0).path("l").asDouble();
        long numberOfTransactions = rootNode.path("results").get(0).path("n").asLong();
        double openPrice = rootNode.path("results").get(0).path("o").asDouble();
        long timestamp = rootNode.path("results").get(0).path("t").asLong();
        double tradingVolume = rootNode.path("results").get(0).path("v").asDouble();

        // Create and return a new StockQuote object
        return new StockQuote(symbol, closePrice, highestPrice, lowestPrice,
                (int) numberOfTransactions, openPrice, timestamp, tradingVolume);
            
    }
}
