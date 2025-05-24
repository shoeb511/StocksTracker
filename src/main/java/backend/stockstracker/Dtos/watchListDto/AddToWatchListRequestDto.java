package backend.stockstracker.Dtos.watchListDto;

import backend.stockstracker.Models.Stock;

public class AddToWatchListRequestDto {

    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
