package dk.diku.TRA.Project.controller;

import dk.diku.TRA.Project.Dtos.PersistStateless.TransformDefinitions;
import dk.diku.TRA.Project.Dtos.TransformDto;
import dk.diku.TRA.Project.Services.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/transform")
public class TransformController {

    @Autowired
    private ResourceService resourceService;


    @CrossOrigin
    @GetMapping(path = "/allTransforms")
    public List<TransformDefinitions> GetAllTransforms() {
        return resourceService.GetAllTransformDefinitions();
    }

}
