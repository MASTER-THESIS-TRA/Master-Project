package dk.diku.TRA.Project.controller;


import dk.diku.TRA.Project.Classes.Transfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @CrossOrigin
    @PostMapping(path = "/createTransfer")
    public Transfer CreateTransfer() {
        return null;
    }
}
