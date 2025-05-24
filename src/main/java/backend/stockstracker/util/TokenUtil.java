package backend.stockstracker.util;

import backend.stockstracker.Exceptions.authExceptions.SessionTimeOutException;
import backend.stockstracker.Exceptions.authExceptions.UnauthorizedUserException;
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

    public boolean validateToken(String token, long userId, String email) throws UnauthorizedUserException, SessionTimeOutException {

        JwtParser parser = Jwts.parser().verifyWith(secretKey).build();

        Claims claims = parser.parseClaimsJws(token).getBody();

        long userIdFromClaims = claims.get("userId", Long.class);
        String userEmail = claims.get("userEmail", String.class);
        long expiry = claims.get("exp", Long.class);

        if(userId != userIdFromClaims){
            System.out.println("Invalid user id..........");
            throw new UnauthorizedUserException("unauthorized access ...");
        }

        if (!userEmail.equals(email)) {
            System.out.println("Invalid user email..........");
            throw new UnauthorizedUserException("unauthorized access ...");
        }

        if (expiry < System.currentTimeMillis()){
            System.out.println("Expired user session..........");
            throw new SessionTimeOutException("session has expired, go and login again ...");
        }

        System.out.println("token has been validated..........");

        return true;
    }
}
