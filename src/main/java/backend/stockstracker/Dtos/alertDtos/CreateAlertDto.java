package backend.stockstracker.Dtos.alertDtos;

import backend.stockstracker.Models.AlertType;

public class CreateAlertDto {

    String symbol;
    double treshHold;
    AlertType alertType;
    String email;
    long userId;
    String token;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getTreshHold() {
        return treshHold;
    }

    public void setTreshHold(double treshHold) {
        this.treshHold = treshHold;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
