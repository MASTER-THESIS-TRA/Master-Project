package dk.diku.TRA.Project.Services;

import dk.diku.TRA.Project.Classes.Resource;
import dk.diku.TRA.Project.Classes.ResourceManager;
import dk.diku.TRA.Project.Classes.Transfer;
import dk.diku.TRA.Project.Dtos.ResourceTypeDto;
import dk.diku.TRA.Project.repository.ResourceRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;
    //private ResourceManager resourceManager;

    public boolean SellResource(Transfer transfer) {
        throw new NotImplementedException();
    }

    public boolean BuyResource(Transfer transfer) {
        throw new NotImplementedException();
    }

    public ResourceTypeDto CreateResourceType(ResourceTypeDto resourceTypeDto) {
        return resourceRepository.save(resourceTypeDto);
    }
    public List<ResourceTypeDto> GetAllResources() {
        return resourceRepository.findAll();
    }
}
