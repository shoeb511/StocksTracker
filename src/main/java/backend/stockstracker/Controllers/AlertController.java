package backend.stockstracker.Controllers;

import backend.stockstracker.Dtos.alertDtos.CreateAlertDto;
import backend.stockstracker.Exceptions.authExceptions.SessionTimeOutException;
import backend.stockstracker.Exceptions.authExceptions.UnauthorizedUserException;
import backend.stockstracker.Exceptions.stockExceptions.AlertNotFoundException;
import backend.stockstracker.Exceptions.stockExceptions.InvalidAlertTypeException;
import backend.stockstracker.Exceptions.stockExceptions.InvalidSymbolException;
import backend.stockstracker.Models.Alert;
import backend.stockstracker.Models.AlertType;
import backend.stockstracker.Models.Stock;
import backend.stockstracker.Repository.AlertRepository;
import backend.stockstracker.service.alertService.AlertService;
import backend.stockstracker.util.TokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlertController {

    private final AlertRepository alertRepository;
    AlertService alertService;
    StockController stockController;
    TokenUtil tokenUtil;

    public AlertController(AlertService alertService, StockController stockController,
                           AlertRepository alertRepository, TokenUtil tokenUtil) {
        this.alertService = alertService;
        this.stockController = stockController;
        this.alertRepository = alertRepository;
        this.tokenUtil = tokenUtil;
    }

    // it will take a auth token as argument. only the valid token user can create alert
    @PostMapping(value = "/alert")
    public Alert createAlert(@RequestBody CreateAlertDto alertDto) throws SessionTimeOutException, InvalidSymbolException, InvalidAlertTypeException, UnauthorizedUserException {

        String token = alertDto.getToken();
        String email = alertDto.getEmail();
        long id = alertDto.getUserId();

        if(!tokenUtil.validateToken(token, id, email)){
            System.out.println("Invalid token token has been checked");
            throw new SessionTimeOutException("go login and try again...");
        }

        Alert alert = new Alert();
        Stock stock = new Stock();

        try{
            stock = stockController.getStock(alertDto.getSymbol());
        }
        catch (Exception e){
            throw new InvalidSymbolException("this symbol does'nt match any stock ...");
        }

        if(!alertDto.getAlertType().equals(AlertType.UP) || !alertDto.getAlertType().equals(AlertType.DOWN)){
            throw new InvalidAlertTypeException("please enter alertType either UP or DOWN ...");
        }

        alert = alertService.createAlert(stock, alertDto.getTreshHold(), alertDto.getAlertType(),
                                                alertDto.getUserId());

         return alert;
    }

    @DeleteMapping(value = "/alert")
    public ResponseEntity<String> deleteAlert(@RequestParam ("id") long id) {
        alertService.deleteAlert(id);
        return new ResponseEntity<>("Alert deleted", HttpStatus.OK);
    }

    @GetMapping(value = "/alert")
    public ResponseEntity<List<Alert>> getAllAlerts(@RequestParam ("id") long userId) throws AlertNotFoundException {

        try{
            List<Alert> alerts = alertService.getAllAlerts(userId);
            return new ResponseEntity<>(alerts, HttpStatus.OK);
        }
        catch (Exception e){
            throw new AlertNotFoundException(e.getMessage());
        }

    }

    private String removeTokenFromAuthHeader(String authHeader) {

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new IllegalArgumentException("invalid auth header");
    }
}
