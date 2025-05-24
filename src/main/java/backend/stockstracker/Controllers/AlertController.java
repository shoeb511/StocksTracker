package backend.stockstracker.Controllers;

import backend.stockstracker.Dtos.alertDtos.CreateAlertDto;
import backend.stockstracker.Exceptions.SessionTimeOutException;
import backend.stockstracker.Models.Alert;
import backend.stockstracker.Models.AlertType;
import backend.stockstracker.Models.Stock;
import backend.stockstracker.Repository.AlertRepository;
import backend.stockstracker.service.alertService.AlertService;
import backend.stockstracker.util.TokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public Alert createAlert(@RequestBody CreateAlertDto alertDto) throws SessionTimeOutException {

        String token = alertDto.getToken();
        String email = alertDto.getEmail();
        long id = alertDto.getUserId();

        if(!tokenUtil.validateToken(token, id, email)){
            System.out.println("Invalid token token has been checked");
            throw new SessionTimeOutException("go login and try again...");
        }

        Stock stock = stockController.getStock(alertDto.getSymbol());

        Alert alert = alertService.createAlert(stock, alertDto.getTreshHold(), alertDto.getAlertType(),
                                                alertDto.getUserId());

         return alert;
    }

    @DeleteMapping(value = "/alert")
    public ResponseEntity<String> deleteAlert(@RequestParam ("id") long id) {
        alertService.deleteAlert(id);
        return new ResponseEntity<>("Alert deleted", HttpStatus.OK);
    }

    @GetMapping(value = "/alert")
    public ResponseEntity<List<Alert>> getAllAlerts(@RequestParam ("id") long userId) {
        List<Alert> alerts = alertService.getAllAlerts(userId);
        if(alerts == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(alerts, HttpStatus.OK);
    }

    private String removeTokenFromAuthHeader(String authHeader) {

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new IllegalArgumentException("invalid auth header");
    }
}
