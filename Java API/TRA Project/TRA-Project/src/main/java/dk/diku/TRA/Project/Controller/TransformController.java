package dk.diku.TRA.Project.controller;

import dk.diku.TRA.Project.Classes.Resource;
import dk.diku.TRA.Project.Classes.Transformation;
import dk.diku.TRA.Project.Dtos.DoTransformDto;
import dk.diku.TRA.Project.Dtos.PersistStateless.TransformDefinitions;
import dk.diku.TRA.Project.Dtos.TransformDto;
import dk.diku.TRA.Project.Services.AgentService;
import dk.diku.TRA.Project.Services.EventService;
import dk.diku.TRA.Project.Services.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/transform")
public class TransformController {

    @Autowired
    ResourceService resourceService;

    @Autowired
    AgentService agentService;

    @Autowired
    EventService eventService;

    @CrossOrigin
    @GetMapping(path = "/allTransforms")
    public List<TransformDefinitions> GetAllTransforms() {
        return resourceService.GetAllTransformDefinitions();
    }

    @CrossOrigin
    @PostMapping(path = "/doTransform")
    public String ApplyTransform(@RequestBody DoTransformDto doTransformDto){
        Resource r = Resource.mult(Resource.ParseStringToResource(doTransformDto.getTransform()),doTransformDto.getAmount());
        Transformation t = new Transformation(agentService.GetAgentById(doTransformDto.getSender()), r);
        if (resourceService.ApplyTransform(t)){
            eventService.RecordEvent("Transform", doTransformDto.getSender(), LocalDateTime.now(),Resource.ToString(r));
            return "Success";
        }
        else{
            return "Error";
        }
    }

}
