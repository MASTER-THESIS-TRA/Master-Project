package dk.diku.TRA.Project.controller;

import Classes.Resource;
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
    public ResourceTransfer ValidateTransfer(@RequestBody ResourceTransfer data) {
        System.out.print("Data: " + data);
        return data;
    }
}
