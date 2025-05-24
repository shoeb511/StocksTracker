package backend.stockstracker.service.alertService;


import backend.stockstracker.ArertStrategy.AlertStrategy;
import backend.stockstracker.ArertStrategy.DownAlertStrategy;
import backend.stockstracker.Exceptions.stockExceptions.AlertNotFoundException;
import backend.stockstracker.Models.Alert;
import backend.stockstracker.Models.AlertStatus;
import backend.stockstracker.Models.AlertType;
import backend.stockstracker.Models.Stock;
import backend.stockstracker.Repository.AlertRepository;
import backend.stockstracker.Repository.StockRepository;
import backend.stockstracker.alertStrategyFactory.AlertStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
@Component
public class AlertService {


    @Autowired
    private TaskScheduler taskScheduler;
    private ScheduledFuture<?> future;

    private final RestTemplate restTemplate;
    private AlertRepository alertRepository;
    private StockRepository stockRepository;



    public AlertService(RestTemplate restTemplate,
                        AlertRepository alertRepository,
                        StockRepository stockRepository) {
        this.restTemplate = restTemplate;
        this.alertRepository = alertRepository;
        this.stockRepository = stockRepository;
    }


    //============ creating alert

    public Alert createAlert(Stock stock, double treshHoldVlaue, AlertType alertType, long userId) {

        Alert newAlert = new Alert();

        newAlert.setStock(stock);
        newAlert.setAlertType(alertType);
        newAlert.setTreshHoldValue(treshHoldVlaue);
        newAlert.setUserId(userId);
        newAlert.setDeleted(false);
        newAlert.setAlertStatus(AlertStatus.ACTIVE);

        alertRepository.save(newAlert);

        startSchedular();
        //Alert ans = alert(newAlert);

        System.out.println("Alert create ho gaya");
        System.out.println(newAlert.toString());

        return newAlert;
    }

    // start alert will scheduled the task which will run every 1 minute
    public void startSchedular(){
        if(future == null || future.isCancelled()){
            future = taskScheduler.scheduleAtFixedRate(this::checAllAlerts, Duration.ofSeconds(60));
        }
    }

    // this method responsilbe for the logic of checking all alerts
    public void checAllAlerts(){
        System.out.println("Running alert check at " + LocalDateTime.now());
        alertRepository.findAllByAlertStatus(AlertStatus.ACTIVE).forEach(this::checkAndTriggerAlert);
    }

    // htis method has been scheduled and will run and check the stock price crossing the limit
    public void checkAndTriggerAlert(Alert alert) {

        String symbol = alert.getStock().getSymbol();

        System.out.println("Alert Service " + LocalDateTime.now());

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

        alert.setStock(stock); // set the current stock data in the alert

        AlertStrategyFactory factory = new AlertStrategyFactory();

        AlertStrategy alertStrategy = factory.getAlertStrategy(alert.getAlertType());

        Alert triggeredAlert =  alertStrategy.triggerAlert(alert);  // will chekk the price and trigger the alert accodingly

        if(triggeredAlert != null){
            if (triggeredAlert.getAlertStatus().equals(AlertStatus.TRIGGERED)){
                stop(triggeredAlert);
                System.out.println("Alert has been triggered" + triggeredAlert.toString());
            }
        }
    }

    // this method will stop the task schedular and will delete the alert from the database
    public void stop(Alert alert) {

        if(!future.isCancelled() || future != null){
            future.cancel(true);
        }

        alert.setDeleted(true);
        alertRepository.save(alert);

        //alertRepository.updateAlert(alert.getAlertStatus(), alert.getId());
        //stockRepository.deleteById(alert.getStock().getId());

        System.out.println("Alert has been stopped and deleted");

    }

    public void deleteAlert(long id) {
        alertRepository.deleteById(id);
    }

    public List<Alert> getAllAlerts(long userId) throws AlertNotFoundException {
        List<Alert> alerts = alertRepository.findAllByUserId(userId);
        if(alerts.isEmpty()){
            System.out.println("Alert list is empty");
            throw new AlertNotFoundException("No alert associated with with whis user id "+ userId);
        }
        return alerts;
    }
}
