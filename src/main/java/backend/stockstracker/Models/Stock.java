package backend.stockstracker.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Stock extends BaseModel{

    public String symbol;
    private double currentPrice;
    private double previousDayClosePrice;
    private double operPriceOfTheDay;
    private double highOftheDay;
    private double lowOftheDay;

    @OneToMany (mappedBy = "stock")
    private List<StckWatchList> watchLists;



    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getPreviousDayClosePrice() {
        return previousDayClosePrice;
    }

    public void setPreviousDayClosePrice(double previousDayClosePrice) {
        this.previousDayClosePrice = previousDayClosePrice;
    }

    public double getOperPriceOfTheDay() {
        return operPriceOfTheDay;
    }

    public void setOperPriceOfTheDay(double operPriceOfTheDay) {
        this.operPriceOfTheDay = operPriceOfTheDay;
    }

    public double getHighOftheDay() {
        return highOftheDay;
    }

    public void setHighOftheDay(double highOftheDay) {
        this.highOftheDay = highOftheDay;
    }

    public double getLowOftheDay() {
        return lowOftheDay;
    }

    public void setLowOftheDay(double lowOftheDay) {
        this.lowOftheDay = lowOftheDay;
    }

    public List<StckWatchList> getWatchLists() {
        return watchLists;
    }

    public void setWatchLists(List<StckWatchList> watchLists) {
        this.watchLists = watchLists;
    }
}
