package dk.diku.TRA.Project.controller;


import dk.diku.TRA.Project.Classes.Resource;
import dk.diku.TRA.Project.Classes.ResourceType;
import dk.diku.TRA.Project.Dtos.ResourceTypeDto;
import dk.diku.TRA.Project.Services.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;


    @CrossOrigin
    @PostMapping(path = "/createResourceType")
    public String CreateResource(@RequestBody ResourceTypeDto resourceTypeDto) {
        return resourceService.CreateResourceType(resourceTypeDto);
    }

    @CrossOrigin
    @DeleteMapping(path = "/deleteResourceByName")
    public void DeleteResourceByName(String resourceName) {

    }

    @CrossOrigin
    @GetMapping(path = "/getAllResources")
    public List<ResourceType> GetAllResources() {
        return resourceService.GetAllResources();
    }
}
