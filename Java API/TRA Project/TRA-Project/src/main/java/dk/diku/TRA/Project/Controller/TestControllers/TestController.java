package dk.diku.TRA.Project.controller.TestControllers;

import dk.diku.TRA.Project.Transfer.ResourceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
