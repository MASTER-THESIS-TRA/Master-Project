package dk.diku.TRA.Project.Services;

import dk.diku.TRA.Project.Classes.ResourceType;
import dk.diku.TRA.Project.Classes.Transfer;
import dk.diku.TRA.Project.Dtos.ResourceTypeDto;
import dk.diku.TRA.Project.repository.ResourceTypeRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    private ResourceTypeRepository resourceTypeRepository;

    public String CreateResourceType(ResourceTypeDto resourceTypeDto) {
        ResourceType e = new ResourceType();
        e.setName(resourceTypeDto.getName());
        e.setWeight(resourceTypeDto.getWeight());
        return resourceTypeRepository.save(e).getId();
    }

    public List<ResourceType> GetAllResources() {
        return resourceTypeRepository.findAll();
    }

    public String DeleteTypeByName(String name){
        if (resourceTypeRepository.countByName(name)>0){
            resourceTypeRepository.deleteByName(name);
            return "Success";
        }
        return "Type does not exist. Try checking spelling.";
    }
}
