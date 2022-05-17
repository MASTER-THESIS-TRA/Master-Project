package dk.diku.TRA.Project.Config;

import dk.diku.TRA.Project.Classes.ResourceManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceManagerConfig {
    @Bean
    public ResourceManager resourceManager() {
        return new ResourceManager("Bank");
    }
}
