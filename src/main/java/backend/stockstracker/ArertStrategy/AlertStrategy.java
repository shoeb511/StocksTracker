package backend.stockstracker.ArertStrategy;

import backend.stockstracker.Models.Alert;
import backend.stockstracker.Models.Stock;

public interface AlertStrategy {

    public Alert triggerAlert(Alert alert);
}
