package dk.diku.TRA.Project.Config;

import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Classes.ResourceManager;
import dk.diku.TRA.Project.repository.AgentRepository;
import dk.diku.TRA.Project.repository.CreditRepository;
import dk.diku.TRA.Project.repository.OwnershipRepository;
import dk.diku.TRA.Project.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.EventListener;

@Configuration
public class ResourceManagerConfig {
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    OwnershipRepository ownershipRepository;
    @Autowired
    WeightRepository weightRepository;

    ResourceManager RM;
    @Bean
    @DependsOn({"creditRepository","ownershipRepository","agentRepository"})
    public ResourceManager resourceManager() {
        RM = new ResourceManager("Bank","bank@bank.dk","bankMCbankerson");
        RM.initRepos(agentRepository,ownershipRepository,creditRepository,weightRepository);
        return RM;
        // agentRepository.save((Agent)rm);
    }
    /*@EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        RM.initRepos(agentRepository,ownershipRepository,creditRepository);
    }*/
}

