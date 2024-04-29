package edu.canisius.csc213.project2.quotes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import edu.canisius.csc213.project2.util.*;

public class PolygonStockQuoteProvider implements StockQuoteProvider{

    @Override
    public StockQuote getStockQuote(String stockQuoteEndpoint) throws IOException {
        String json = sendGetRequest(stockQuoteEndpoint);
        PolygonJsonReplyTranslator jft = new PolygonJsonReplyTranslator();
        return jft.translateJsonToFinancialInstrument(json);

    }

    public static String sendGetRequest(String endpointUrl) throws IOException {
        URL url = new URL(endpointUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            throw new IOException("Failed to send GET request. Response code: " + responseCode);
        }
    }

    @Override
    public String getEndpointUrl(String symbolName, String date, String apiKey) {
        try {
            // Validate date format
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

            // Encode parameters to ensure URL safety
            String encodedSymbolName = URLEncoder.encode(symbolName, StandardCharsets.UTF_8.toString());
            String encodedDate = URLEncoder.encode(date, StandardCharsets.UTF_8.toString());
            String encodedApiKey = URLEncoder.encode(apiKey, StandardCharsets.UTF_8.toString());
        
            // Construct the URL with the encoded parameters
            String endpointUrl = "https://api.polygon.io/v2/aggs/ticker/" + encodedSymbolName +
                "/range/1/day/" + encodedDate + "/" + encodedDate + "?apiKey=" + encodedApiKey;
        
            return endpointUrl;
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            throw new IllegalArgumentException("Invalid date format: " + date, e);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return null;
        }
    }


}
