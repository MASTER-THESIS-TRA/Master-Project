package dk.diku.TRA.Project.Services;


import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Classes.ResourceManager;
import dk.diku.TRA.Project.Classes.Utils;
import dk.diku.TRA.Project.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;
    @Autowired
    ResourceManager resourceManager;

    Utils utils = new Utils();

    public Agent GetAgentById(String id){
        return agentRepository.findById(id).orElse(null);
    }

    public Agent GetAgentByEmail(String email){
        return agentRepository.findAgentByEmail(email);
    }

    public String SaveAgent (String name, String email, String password){
        if (!utils.VerifyEmail(email)){
            return "error";
        }
        Agent agent = new Agent();
        agent.setName(name);
        agent.setEmail(email);
        agent.setPassword(password);
        Agent a = agentRepository.save(agent);
        resourceManager.AddAgent(a);
        return a.getUuid();
    }

    public List<Agent> GetAllAgents(){
        return agentRepository.findAll();
    }

    public boolean existsAgent(String id){
        return agentRepository.findById(id).isPresent();
    }

    public Map<String, String> validateLogin(String email, String password){
        HashMap<String, String> userInfo = new HashMap<>();
        Agent a = agentRepository.findAgentByEmail(email);
        if (a != null && a.getPassword().equals(password)){
            userInfo.put("user", a.getUuid());
            userInfo.put("role", a.getName());
            return userInfo;
        }
        userInfo.put("error", "error");
        return userInfo;
    }

    public void DeleteById(String id){
        agentRepository.deleteById(id);
    }
}
