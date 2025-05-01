package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
public class Alert extends BaseModel{

    @ManyToOne
    private User user;

    @ManyToOne
    private Stock stock;
    private double treshHoldValue;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
