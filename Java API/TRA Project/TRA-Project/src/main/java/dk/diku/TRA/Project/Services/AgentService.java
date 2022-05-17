package dk.diku.TRA.Project.Services;


import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    public Agent GetAgentById(String id){
        return agentRepository.findById(id).get();
    }

    public String SaveAgent (String name, String email, String password){
        Agent agent = new Agent();
        agent.setName(name);
        agent.setEmail(email);
        agent.setPassword(password);
        return agentRepository.save(agent).getUuid();
    }

    public List<Agent> GetAllAgents(){
        return agentRepository.findAll();
    }
}
