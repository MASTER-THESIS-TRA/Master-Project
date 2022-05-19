package dk.diku.TRA.Project.Controller;

import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Classes.ResourceType;
import dk.diku.TRA.Project.Classes.Transformation;
import dk.diku.TRA.Project.Dtos.AgentDto;
import dk.diku.TRA.Project.Dtos.ResourceTypeDto;
import dk.diku.TRA.Project.Services.AgentService;
import dk.diku.TRA.Project.Services.ResourceService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AgentService agentService;
    @Autowired
    private ResourceService resourceService;

    @CrossOrigin
    @GetMapping(path = "/getAgentInfo")
    public @ResponseBody
    String getAgentInfo() {
        JSONObject userData = new JSONObject();
        userData.put("id", UUID.randomUUID());
        userData.put("name", "Alexander Borgert");
        userData.put("balance", new Random().nextInt(1333337));
        userData.put("pendingTransactions", 4);
        return userData.toString();
    }

    @CrossOrigin
    @PostMapping(path = "/newAgent")
    public String NewAgent(@RequestBody AgentDto agentDto) {
        return agentService.SaveAgent(agentDto.name,agentDto.email,agentDto.password);
    }

    @CrossOrigin
    @GetMapping(path = "/allAgents")
    public List<Agent> AllAgents(){
        return agentService.GetAllAgents();
    }

    @CrossOrigin
    @GetMapping(path="/existsAgent")
    public boolean ExistsAgent(String id){
        return agentService.existsAgent(id);
    }

    @CrossOrigin
    @PostMapping(path = "createTransform")
    public Transformation CreateTransformation() {
        return null;
    }



    @CrossOrigin
    @PostMapping(path = "/createResourceType")
    public String CreateResource(@RequestBody ResourceTypeDto resourceTypeDto) {
        return resourceService.CreateResourceType(resourceTypeDto);
    }

    @CrossOrigin
    @DeleteMapping(path = "/deleteResourceByName")
    public void DeleteResourceByName(String resourceName) {
        resourceService.DeleteTypeByName(resourceName);
    }

    @CrossOrigin
    @GetMapping(path = "/getAllResources")
    public List<ResourceType> GetAllResources() {
        return resourceService.GetAllResources();
    }
}
