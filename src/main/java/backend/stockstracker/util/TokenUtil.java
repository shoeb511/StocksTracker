package backend.stockstracker.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class TokenUtil {

    @Autowired
    SecretKey secretKey;

    public boolean validateToken(String token, long userId, String email) {

        JwtParser parser = Jwts.parser().verifyWith(secretKey).build();

        Claims claims = parser.parseClaimsJws(token).getBody();

        long userIdFromClaims = claims.get("userId", Long.class);
        String userEmail = claims.get("userEmail", String.class);
        long expiry = claims.get("exp", Long.class);

        if(userId != userIdFromClaims){
            System.out.println("Invalid user id..........");
            return false;
        }

        if (!userEmail.equals(email)) {
            System.out.println("Invalid user email..........");
            return false;
        }

        if (expiry < System.currentTimeMillis()){
            System.out.println("Expired user session..........");
            return false;
        }

        System.out.println("token has been validated..........");

        return true;
    }
}
