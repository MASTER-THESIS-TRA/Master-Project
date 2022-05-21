package dk.diku.TRA.Project.Config;

import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Classes.ResourceManager;
import dk.diku.TRA.Project.repository.AgentRepository;
import dk.diku.TRA.Project.repository.CreditRepository;
import dk.diku.TRA.Project.repository.OwnershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ResourceManagerConfig {
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    OwnershipRepository ownershipRepository;

    @Bean
    @DependsOn({"creditRepository","ownershipRepository","agentRepository"})
    public ResourceManager resourceManager() {
        ResourceManager RM = new ResourceManager("Bank","bank@bank.dk","bankMCbankerson");
        RM.initRepos(agentRepository,ownershipRepository,creditRepository);
        //ResourceManager agent = (ResourceManager)agentRepository.save((Agent)RM);
        //RM.setUuid(agent.getUuid());
        return RM;
        // agentRepository.save((Agent)rm);
    }
}
