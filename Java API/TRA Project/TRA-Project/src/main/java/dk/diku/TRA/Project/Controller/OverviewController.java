package dk.diku.TRA.Project.Controller;

import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Dtos.ResourceDto;
import dk.diku.TRA.Project.Services.AgentService;
import dk.diku.TRA.Project.Services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(path="/getBalance")
    public List<ResourceDto> GetBalance(String id){
        return resourceService.GetBalanceById(id);
    }

    @CrossOrigin
    @GetMapping(path = "/getAgent")
    public Agent GetAgent(String id){
        return agentService.GetAgentById(id);
    }

}
