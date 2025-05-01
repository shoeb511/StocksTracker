package backend.stockstracker.stockService;


import backend.stockstracker.Models.Stock;
import backend.stockstracker.Models.WatchList;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class StockService {

    public final RestTemplate restTemplate;



    public StockService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Stock getStockBySymbol(String symbol) {

        String apiKey = "d06h0a9r01qg26s7t9v0d06h0a9r01qg26s7t9vg";  // yeh tumhari working key hai
        String url = "https://finnhub.io/api/v1/quote?symbol=" + symbol + "&token=" + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");  // Yeh zaroori hai kuch APIs ke liye

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Map.class
        );

        Map<String, Object> responseBody = response.getBody();

        Stock stock = new Stock();

        stock.setSymbol(symbol);
        stock.setCurrentPrice(Double.valueOf(responseBody.get("c").toString()));
        stock.setOperPriceOfTheDay(Double.valueOf(responseBody.get("o").toString()));
        stock.setHighOftheDay(Double.valueOf(responseBody.get("h").toString()));
        stock.setLowOftheDay(Double.valueOf(responseBody.get("l").toString()));
        stock.setPreviousDayClosePrice(Double.valueOf(responseBody.get("pc").toString())); // 'pc' correct hai close price ke liye

        return stock;

//        String apiKey = "d06h0a9r01qg26s7t9v0d06h0a9r01qg26s7t9vg";
//        String url = "https://finnhub.io/api/v1/quote?symbol=" + symbol + "&token=" + apiKey;
//
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.set("user-agent", "Mozilla/5.0");
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<Map> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                entity,
//                Map.class
//        );
//
//        Map<String, Object> responseBody = response.getBody();
//
//        Stock stock = new Stock();
//
//        // c = current price
//        // h = high of the day
//        // l = low of the day
//        // o = open of the day
//        // cp = close of previous day
//
//        stock.setSymbol(symbol);
//        stock.setCurrentPrice(Double.valueOf(responseBody.get("c").toString()));
//        stock.setOperPriceOfTheDay(Double.valueOf(responseBody.get("o").toString()));
//        stock.setHighOftheDay(Double.valueOf(responseBody.get("h").toString()));
//        stock.setLowOftheDay(Double.valueOf(responseBody.get("l").toString()));
//        stock.setPreviousDayClosePrice(Double.valueOf(responseBody.get("pc").toString()));
//
//        return stock;
    }

    public WatchList addStockToWatchList(Stock stock){
        return null;
    }
}
//{
//        "symbol": "asdfj"
//        }