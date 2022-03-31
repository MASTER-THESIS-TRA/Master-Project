package Controllers;


import Classes.Resource;
import Classes.ResourceManager;
import Classes.Transfer;
import Services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @Autowired
    ResourceService resourceService;


    @PostMapping
    public ResponseEntity<String> SellResource(ResourceManager resourceManager, Transfer transfer) {
        resourceService.SellResource(resourceManager, transfer);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<String> BuyResource(ResourceManager resourceManager, Transfer transfer) {
        resourceService.BuyResource(resourceManager, transfer);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
