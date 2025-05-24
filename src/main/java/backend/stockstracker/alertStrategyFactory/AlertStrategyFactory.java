package backend.stockstracker.alertStrategyFactory;

import backend.stockstracker.ArertStrategy.AlertStrategy;
import backend.stockstracker.ArertStrategy.DownAlertStrategy;
import backend.stockstracker.ArertStrategy.UpAlertStrategy;
import backend.stockstracker.Models.AlertType;

public class AlertStrategyFactory {

    AlertStrategy alertStrategy;

    public AlertStrategy getAlertStrategy(AlertType alertType) {
        switch (alertType) {
            case UP -> alertStrategy = new UpAlertStrategy();
            case DOWN -> alertStrategy = new DownAlertStrategy();
        }
        return alertStrategy;
    }
}
