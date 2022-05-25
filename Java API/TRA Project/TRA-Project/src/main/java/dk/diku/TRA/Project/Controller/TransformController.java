package dk.diku.TRA.Project.controller;

import dk.diku.TRA.Project.Classes.Resource;
import dk.diku.TRA.Project.Classes.Transformation;
import dk.diku.TRA.Project.Dtos.DoTransformDto;
import dk.diku.TRA.Project.Dtos.PersistStateless.TransformDefinitions;
import dk.diku.TRA.Project.Dtos.TransformDto;
import dk.diku.TRA.Project.Services.AgentService;
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
    ResourceService resourceService;

    @Autowired
    AgentService agentService;

    @CrossOrigin
    @GetMapping(path = "/allTransforms")
    public List<TransformDefinitions> GetAllTransforms() {
        return resourceService.GetAllTransformDefinitions();
    }

    @CrossOrigin
    @PostMapping(path = "/doTransform")
    public String ApplyTransform(@RequestBody DoTransformDto doTransformDto){
        Transformation t = new Transformation(agentService.GetAgentById(doTransformDto.getSender()),
                Resource.mult(Resource.ParseStringToResource(doTransformDto.getTransform()),doTransformDto.getAmount()));
        if (resourceService.ApplyTransform(t)){
            return "Success";
        }
        else{
            return "Error";
        }
    }

}
