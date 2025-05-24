package backend.stockstracker.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {


    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
