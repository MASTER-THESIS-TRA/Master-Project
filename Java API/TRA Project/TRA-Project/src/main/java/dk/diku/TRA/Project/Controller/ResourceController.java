package dk.diku.TRA.Project.controller;


import dk.diku.TRA.Project.Classes.Resource;
import dk.diku.TRA.Project.Services.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/resource")
public class ResourceController {

    private ResourceService resourceService;


    @CrossOrigin
    @PostMapping(path = "/createResource")
    public Resource CreateResource() {
        return null;
    }

    @CrossOrigin
    @DeleteMapping(path = "/deleteResourceByName")
    public void DeleteResourceByName(String resourceName) {

    }

    @CrossOrigin
    @GetMapping(path = "/getAllResources")
    public List<Resource> GetAllResources() {
        return resourceService.GetAllResources();
    }
}
