package backend.stockstracker.Configurations;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class SecurityConfig {

    @Bean
    public SecretKey secretKey(@Value("${jwt.secret}") String secret) {

        byte[] keyBytes = secret.getBytes();

        if(keyBytes.length < 32){
            throw new IllegalArgumentException("Secret key must be at least 32 bytes");
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
