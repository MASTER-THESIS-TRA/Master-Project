package dk.diku.TRA.Project.Controller;

import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Classes.GeoCoordinate;
import dk.diku.TRA.Project.Classes.ResourceType;
import dk.diku.TRA.Project.Classes.Transformation;
import dk.diku.TRA.Project.Dtos.AgentDto;
import dk.diku.TRA.Project.Dtos.LocationDto;
import dk.diku.TRA.Project.Dtos.PersistStateless.GiveResourceDto;
import dk.diku.TRA.Project.Dtos.ResourceTypeDto;
import dk.diku.TRA.Project.Dtos.TransformDto;
import dk.diku.TRA.Project.Services.AgentService;
import dk.diku.TRA.Project.Services.LocationService;
import dk.diku.TRA.Project.Services.ResourceService;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    private LocationService locationService;

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
    @RequestMapping(value = "/existsAgent/{id}", method = RequestMethod.GET)
    public boolean ExistsAgent(@PathVariable String id){

        return agentService.existsAgent(id);
    }

    @CrossOrigin
    @PostMapping(path = "createTransform")
    public String CreateTransformation(TransformDto transformDto) {
        if (resourceService.CreateTransform(transformDto)){
            return "Transformation with name: \"" + transformDto.getName() + "\" created.";
        }
        return "Error creating transformation! Transformations cannot created more than they consume.";
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

    @CrossOrigin
    @PostMapping(path = "/giveResource")
    public String GiveResource(@RequestBody GiveResourceDto giveResourceDto){
        System.out.println(giveResourceDto.getEmail() + " " + giveResourceDto.getResourceType());
        if(resourceService.GiveResource(giveResourceDto)){
            return "Success";
        }
        return "error";
    }

    @CrossOrigin
    @GetMapping(path = "/getLocations")
    public List<GeoCoordinate> GetAllLocations() {
        return locationService.GetAllLocations();
    }

    @CrossOrigin
    @PostMapping(path = "/saveLocation")
    public String SaveLocations(@RequestBody LocationDto locationDto) {
        return locationService.SaveLocaition(locationDto.getName(), locationDto.getLongitude(), locationDto.getLatitude());
    }

}
