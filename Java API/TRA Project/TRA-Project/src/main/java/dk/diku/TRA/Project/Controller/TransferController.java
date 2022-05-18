package dk.diku.TRA.Project.Controller;


import dk.diku.TRA.Project.Classes.Transfer;
import dk.diku.TRA.Project.Dtos.TransferDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @CrossOrigin
    @PostMapping(path = "/createTransfer")
    public TransferDto CreateTransfer(@RequestBody TransferDto transferDto) {
        return transferDto;
    }
}
