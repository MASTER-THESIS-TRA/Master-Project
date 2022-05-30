package dk.diku.TRA.Project.Controller;

import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Dtos.ResourceDto;
import dk.diku.TRA.Project.Services.AgentService;
import dk.diku.TRA.Project.Services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/overview")
public class OverviewController {
    @Autowired
    AgentService agentService;
    @Autowired
    ResourceService resourceService;

    @CrossOrigin
    @RequestMapping(value = "/getBalance/{id}", method = RequestMethod.GET)
    public List<ResourceDto> GetBalance(@PathVariable String id){
        return resourceService.GetBalanceById(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/getAgent/{id}", method = RequestMethod.GET)
    public Agent GetAgent(@PathVariable String id){
        return agentService.GetAgentById(id);
    }

}
