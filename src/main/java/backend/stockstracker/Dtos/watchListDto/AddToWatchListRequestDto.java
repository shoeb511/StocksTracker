package Dtos.watchListDto;

import Models.Stock;

public class AddToWatchListRequestDto {

    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
