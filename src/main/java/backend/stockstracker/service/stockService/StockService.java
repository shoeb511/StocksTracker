package backend.stockstracker.service.stockService;


import backend.stockstracker.Models.Stock;
import backend.stockstracker.Models.WatchList;
import backend.stockstracker.Repository.StockRepository;
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
    private StockRepository stockRepository;

    public StockService(RestTemplate restTemplate, StockRepository stockRepository) {
        this.restTemplate = restTemplate;
        this.stockRepository = stockRepository;
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

        stockRepository.save(stock);

        return stock;

    }


    //add new stock to database
    public Stock addStock(String symbol, double currentPrice, double operPriceOfTheDay, double highOftheDay, double lowOftheDay, double previousDayClosePrice) {

        Stock stock = stockRepository.findBySymbol(symbol);

        if (stock == null) {
            System.out.println("Stock not found");
            return null;
        }

        Stock newStock = new Stock();
        newStock.setSymbol(symbol);
        newStock.setCurrentPrice(currentPrice);
        newStock.setOperPriceOfTheDay(operPriceOfTheDay);
        newStock.setHighOftheDay(highOftheDay);
        newStock.setLowOftheDay(lowOftheDay);
        newStock.setPreviousDayClosePrice(previousDayClosePrice);

        stockRepository.save(newStock);

        return  newStock;
    }


    // update an existing stock and add instead if not found in database
    public Stock updateStock(String symbol, double currentPrice, double operPriceOfTheDay, double highOftheDay, double lowOftheDay, double previousDayClosePrice) {

        Stock stock = stockRepository.findBySymbol(symbol);

        if (stock == null) {
            addStock(symbol, currentPrice, operPriceOfTheDay, highOftheDay, lowOftheDay, previousDayClosePrice);
        }

        stockRepository.deleteById(stock.getId());

        Stock newStock = new Stock();
        newStock.setSymbol(symbol);
        newStock.setCurrentPrice(currentPrice);
        newStock.setOperPriceOfTheDay(operPriceOfTheDay);
        newStock.setHighOftheDay(highOftheDay);
        newStock.setLowOftheDay(lowOftheDay);
        newStock.setPreviousDayClosePrice(previousDayClosePrice);
        stockRepository.save(newStock);

        return newStock;
    }


    // remove a stock by symbol
    public Stock deleteStock(String symbol) {
        Stock stock = stockRepository.findBySymbol(symbol);
        if (stock == null) {
            return null;
        }

        stockRepository.delete(stock);
        return stock;
    }


    public WatchList addStockToWatchList(Stock stock){
        return null;
    }
}
