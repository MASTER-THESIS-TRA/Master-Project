package dk.diku.TRA.Project.controller;


import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Services.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @PostMapping(path = "/createAgent")
    public Agent CreateAgent() {
        return new Agent("Agent 007","","","");
    }


}
