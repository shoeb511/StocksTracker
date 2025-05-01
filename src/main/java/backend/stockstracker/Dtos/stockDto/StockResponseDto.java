package Dtos.stockDto;

import Models.Stock;

public class StockResponseDto {

    private Stock stock;
    private StockResponseStatus status;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public StockResponseStatus getStatus() {
        return status;
    }

    public void setStatus(StockResponseStatus status) {
        this.status = status;
    }
}
