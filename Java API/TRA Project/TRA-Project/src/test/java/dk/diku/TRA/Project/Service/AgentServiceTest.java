package dk.diku.TRA.Project.Service;


import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Services.AgentService;
import dk.diku.TRA.Project.repository.AgentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@DataJpaTest
public class AgentServiceTest {

    private AgentService agentService;




    @Test
    public void miv() {
        Agent agent = new Agent();
        agent.setEmail("test@email.com");
        agent.setName("Test");
        agent.setPassword("1234");

        Agent newAgent = agentService.GetAgentByEmail(agent.getEmail());

        Assert.assertNotNull(newAgent);
    }

}
