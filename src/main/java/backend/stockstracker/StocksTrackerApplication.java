package backend.stockstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.websocket.servlet.UndertowWebSocketServletWebServerCustomizer;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
public class StocksTrackerApplication {

    public static void main(String[] args) throws DeploymentException, URISyntaxException, IOException {
        SpringApplication.run(StocksTrackerApplication.class, args);

//        String apiKey = "d058lr1r01qoigrt2jugd058lr1r01qoigrt2jv0";
//        String symbol = "AAPL";
//        String url = "https://finnhub.io/api/v1/quote?symbol=" + symbol + "&token=" + apiKey;
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        try {
//            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
//            Map<String, Object> responseData = response.getBody();
//            if (responseData != null) {
//                System.out.println("Current price = "+ responseData.get("c"));
//                System.out.println("High price of the day " + responseData.get("h"));
//                System.out.println("Low price of the day " + responseData.get("l"));
//                System.out.println("Open price of the day " + responseData.get("o"));
//                System.out.println("Previous day close price  " + responseData.get("cp"));
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }

    }


}
