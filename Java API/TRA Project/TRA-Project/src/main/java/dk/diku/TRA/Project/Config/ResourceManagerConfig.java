package dk.diku.TRA.Project.Config;

import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Classes.ResourceManager;
import dk.diku.TRA.Project.repository.AgentRepository;
import dk.diku.TRA.Project.repository.CreditRepository;
import dk.diku.TRA.Project.repository.OwnershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceManagerConfig {
    @Autowired
    AgentRepository agentRepository;
    /*@Autowired
    CreditRepository creditRepository;
    @Autowired
    OwnershipRepository ownershipRepository;*/

    @Bean
    public ResourceManager resourceManager() {
        ResourceManager rm = new ResourceManager("Bank","bank@bank.dk","bankMCbankerson");
        return rm;//(ResourceManager) agentRepository.save((Agent)rm);
    }
}
