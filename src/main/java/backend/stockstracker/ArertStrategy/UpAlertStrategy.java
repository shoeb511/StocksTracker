package backend.stockstracker.ArertStrategy;

import backend.stockstracker.Models.Alert;
import backend.stockstracker.Models.AlertStatus;
import backend.stockstracker.Models.Stock;

public class UpAlertStrategy implements AlertStrategy {

    @Override
    public Alert triggerAlert(Alert alert) {
        double currentPrice = alert.getStock().getCurrentPrice();
        double treshold = alert.getTreshHoldValue();

        if (currentPrice >= alert.getTreshHoldValue()) {
            System.out.println("Up Alert has been triggerd for the symbol " + alert.getStock().getSymbol());
            System.out.println("the stock price has crossed by " + (currentPrice - treshold));
            alert.setAlertStatus(AlertStatus.TRIGGERED);
        }

        return alert;
    }
}
