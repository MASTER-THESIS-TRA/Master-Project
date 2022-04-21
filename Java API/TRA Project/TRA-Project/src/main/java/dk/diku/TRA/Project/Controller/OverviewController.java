package dk.diku.TRA.Project.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/")
public class OverviewController {

    @CrossOrigin
    @GetMapping(path="/pendingTransactions")
    public ResponseEntity getPendingTransactions(){
        // Find the user/actor based off of the context and fetch pending transactions
        // Call to database:
        /*Map<String, String> body = new HashMap<>();//generateFillerTableData();
        body.put("weirdFieldNameThatHasDefinitelyNotBeenUsedAnywhereElse","valueThatCorrespondsToTheLongField");
        // Probably some error handling here:
        HttpStatus status = HttpStatus.ACCEPTED;
        return ResponseHandler.generateResponse("List of pending transactions", status, body);*/
        return new ResponseEntity("Success",HttpStatus.ACCEPTED);
    }

    private Map generateFillerTableData(){
        Map data = new HashMap<>();
        List<Map> elements = new ArrayList();
        elements.add(makeMapWithElement("Frozen yoghurt", "159", "6.0", "24", "4.0"));
        elements.add(makeMapWithElement("Ice cream sandwich", "237", "9.0", "37", "4.3"));
        elements.add(makeMapWithElement("Eclair", "262", "16.0", "24", "6.0"));
        elements.add(makeMapWithElement("Cupcake", "305", "3.7", "67", "4.3"));
        elements.add(makeMapWithElement("Gingerbread", "356", "16.0", "49", "3.9"));
        data.put("table",elements);
        return data;
    }
    private Map makeMapWithElement(String name, String calories, String fat, String carbs, String protein){
        Map element = new HashMap<>();
        element.put("Name",name);
        element.put("Calories", calories);
        element.put("Fat", fat);
        element.put("Carbs", carbs);
        element.put("Protein", protein);
        return element;
    }
}
