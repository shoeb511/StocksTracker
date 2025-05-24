package backend.stockstracker.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class Alert extends BaseModel{

    private long userId;

    @ManyToOne ()
    private Stock stock;
    private double treshHoldValue;

    @Enumerated(EnumType.STRING)
    private AlertType alertType;

    @Enumerated(EnumType.STRING)
    private AlertStatus alertStatus;

    public AlertStatus getAlertStatus() {
        return alertStatus;
    }

    public void setAlertStatus(AlertStatus alertStatus) {
        this.alertStatus = alertStatus;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public double getTreshHoldValue() {
        return treshHoldValue;
    }

    public void setTreshHoldValue(double treshHoldValue) {
        this.treshHoldValue = treshHoldValue;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "alertType=" + alertType +
                ", alertStatus=" + alertStatus +
                ", treshHoldValue=" + treshHoldValue +
                ", stock=" + stock +
                '}';
    }
}
