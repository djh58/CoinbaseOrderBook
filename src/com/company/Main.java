package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class Main {


    // Uncomment for Method 1...
    // private static HttpURLConnection connection;

    public static void main(String[] args) {
        // Method 1: java.net.HttpURLConnection
        /*BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("https://public.sandbox.pro.coinbase.com");
            connection = (HttpURLConnection) url.openConnection();
            // Request setup (5 second timeout)
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            System.out.println(status);
            //PROBLEM: returns 404 and not 200
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        parse(responseContent.toString());
        */

        // Method 2: java.net.http.HttpClient
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.pro.coinbase.com/products/ETH-USD/book?level=2")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(Main::parse)
                .join();
    }

    public static String parse(String responseBody) {
        JSONObject obj = new JSONObject(responseBody);
//        List<Order> bids = (List<Order>) obj.get("bids");
        JSONArray bids = obj.getJSONArray("bids");
        System.out.println("ETH-USD Bids...");
        for(int i = 0; i < bids.length(); i++) {
            JSONArray individualBid = (JSONArray) bids.get(i);
            String price = (String) individualBid.get(0);
            String size = (String) individualBid.get(1);
            int numOrders = (int) individualBid.get(2);
            System.out.println("PRICE: " + price + " SIZE: " + size + " ORDERS: " + numOrders);
        }

        JSONArray asks = obj.getJSONArray("asks");
        System.out.println("ETH-USD Asks...");
        for(int i = 0; i < asks.length(); i++) {
            JSONArray individualAsk = (JSONArray) asks.get(i);
            String price = (String) individualAsk.get(0);
            String size = (String) individualAsk.get(1);
            int numOrders = (int) individualAsk.get(2);
            System.out.println("PRICE: " + price + " SIZE: " + size + " ORDERS: " + numOrders);
        }


        return null;

    }
}