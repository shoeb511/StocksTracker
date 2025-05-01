package Controllers;

import Dtos.stockDto.StockResponseDto;
import Dtos.stockDto.StockRequestDto;
import Dtos.stockDto.StockResponseStatus;
import Dtos.watchListDto.AddToWatchListRequestDto;
import Dtos.watchListDto.WatchListRequestDto;
import Dtos.watchListDto.WatchListResponseDto;
import Dtos.watchListDto.WatchListResponseStatus;
import Models.Stock;
import Models.WatchList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import stockService.StockService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockController {

    StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping(value = "/stock")
    public StockResponseDto getStock(@RequestParam("symbol") String symbol) {

        StockResponseDto responseDto = new StockResponseDto();
        Stock stcok = stockService.getStockBySymbol(symbol);
        responseDto.setStock(stcok);
        responseDto.setStatus(StockResponseStatus.SUCCESS);
        return responseDto;
    }

    @GetMapping(value = "watchList")
    public WatchListResponseDto getWatchList(WatchListRequestDto watchListRequestDto){
        return null;
    }

    @PostMapping(value = "watchList")
    public WatchListResponseDto createWatchList(WatchListRequestDto watchListRequestDto){
        return null;
    }

    @PostMapping(value = "addToWatchList")
    public WatchListResponseDto addToWatchList(AddToWatchListRequestDto addToWatchListRequestDto){

        WatchListResponseDto responseDto = new WatchListResponseDto();
        WatchList watchList = stockService.addStockToWatchList(addToWatchListRequestDto.getStock());
        //responseDto.setWatchList(stocks);
        responseDto.setStatus(WatchListResponseStatus.SUCCESS);

        return responseDto;
    }
}
