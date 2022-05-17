package dk.diku.TRA.Project.controller.TestControllers;

import Classes.Resource;
import Models.Transfer.ResourceDto;
import Models.Transfer.TransferDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

    @CrossOrigin
    @PostMapping(path = "/validateTransfer")
    public List<List<ResourceDto>> ValidateTransfer(@RequestBody List<List<ResourceDto>> data) {
        System.out.print("Data: " + data);
        return data;
    }
}
