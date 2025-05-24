package backend.stockstracker.Controllers;

import backend.stockstracker.Dtos.stockDto.StockResponseDto;
import backend.stockstracker.Dtos.stockDto.StockResponseStatus;
import backend.stockstracker.Dtos.watchListDto.AddToWatchListRequestDto;
import backend.stockstracker.Dtos.watchListDto.WatchListRequestDto;
import backend.stockstracker.Dtos.watchListDto.WatchListResponseDto;
import backend.stockstracker.Dtos.watchListDto.WatchListResponseStatus;
import backend.stockstracker.Models.Stock;
import backend.stockstracker.Models.WatchList;
import org.springframework.web.bind.annotation.*;
import backend.stockstracker.service.stockService.StockService;

@RestController
@RequestMapping("/api")
public class StockController {

    StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    //============ get stock from database by symbol ================================
    @GetMapping(value = "/stock")
    public Stock getStock(@RequestParam("symbol") String symbol) {

        //StockResponseDto responseDto = new StockResponseDto();
        Stock stcok = stockService.getStockBySymbol(symbol);
        if(stcok == null) {
            return  null;
        }
        //responseDto.setStock(stcok);
        //responseDto.setStatus(StockResponseStatus.SUCCESS);
        return stcok;
    }
    //=============================================================================


    //======================== add stock to the database ==========================



//    @GetMapping(value = "watchList")
//    public WatchListResponseDto getWatchList(WatchListRequestDto watchListRequestDto){
//        return null;
//    }
//
//    @PostMapping(value = "watchList")
//    public WatchListResponseDto createWatchList(WatchListRequestDto watchListRequestDto){
//        return null;
//    }
//
//    @PostMapping(value = "addToWatchList")
//    public WatchListResponseDto addToWatchList(AddToWatchListRequestDto addToWatchListRequestDto){
//
//        WatchListResponseDto responseDto = new WatchListResponseDto();
//        WatchList watchList = stockService.addStockToWatchList(addToWatchListRequestDto.getStock());
//        //responseDto.setWatchList(stocks);
//        responseDto.setStatus(WatchListResponseStatus.SUCCESS);
//
//        return responseDto;
//    }
}
