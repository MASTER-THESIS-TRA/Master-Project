package Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/")
public class OverviewController {

    public OverviewController(){
    }

    @GetMapping(path="/pendingTransactions")
    public ResponseEntity<String> getPendingTransactions(){
        // Find the user/actor based off of the context and fetch pending transactions
        Map<String, String> body = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        ResponseEntity<String> response = new ResponseEntity<String>(String.valueOf(headers), status);
        return response;
    }
}
