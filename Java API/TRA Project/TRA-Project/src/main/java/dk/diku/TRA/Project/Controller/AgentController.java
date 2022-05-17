package dk.diku.TRA.Project.controller;


import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Services.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    AgentService agentService;

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

    /**
     * @param name The name of the new agent
     * @param email The email of the new agent
     * @param password The password for the new agent
     * @return The uuid of the newly created Agent
     */
    @CrossOrigin
    @PostMapping(path = "/newAgent")
    public String NewAgent(String name, String email, String password) {
        return agentService.SaveAgent(name,email,password);
    }

    @CrossOrigin
    @GetMapping(path = "/allAgents")
    public List<Agent> AllAgents(){
        return agentService.GetAllAgents();
    }
}