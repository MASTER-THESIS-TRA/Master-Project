package dk.diku.TRA.Project.Services;


import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    public Agent getAgentById(String id){
        return agentRepository.findById(id).get();
    }
}
