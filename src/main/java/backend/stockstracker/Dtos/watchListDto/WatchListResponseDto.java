package Dtos.watchListDto;

import Models.Stock;

import java.util.List;

public class WatchListResponseDto {

    List<Stock> watchList;

    WatchListResponseStatus status;

    public WatchListResponseStatus getStatus() {
        return status;
    }

    public void setStatus(WatchListResponseStatus status) {
        this.status = status;
    }

    public List<Stock> getWatchList() {
        return watchList;
    }

    public void setWatchList(List<Stock> watchList) {
        this.watchList = watchList;
    }
}
