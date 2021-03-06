package dk.diku.TRA.Project.Services;

import dk.diku.TRA.Project.Classes.*;
import dk.diku.TRA.Project.Dtos.PersistStateless.GiveResourceDto;
import dk.diku.TRA.Project.Dtos.PersistStateless.TransformDefinitions;
import dk.diku.TRA.Project.Dtos.ResourceDto;
import dk.diku.TRA.Project.Dtos.ResourceTypeDto;
import dk.diku.TRA.Project.Dtos.TransformDto;
import dk.diku.TRA.Project.Exceptions.TRAException;
import dk.diku.TRA.Project.repository.ResourceTypeRepository;
import dk.diku.TRA.Project.repository.TransformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.Transform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceService {
    @Autowired
    private ResourceTypeRepository resourceTypeRepository;
    @Autowired
    private ResourceManager resourceManager;
    @Autowired
    private TransformRepository transformRepository;
    @Autowired
    private AgentService agentService;

    public String CreateResourceType(ResourceTypeDto resourceTypeDto) {
        ResourceType e = new ResourceType();
        e.setName(resourceTypeDto.getName());
        e.setWeight(resourceTypeDto.getWeight());
        resourceManager.AddWeight(new Weight(resourceTypeDto.getName(),resourceTypeDto.getWeight()));
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


    public List<TransformDefinitions> GetAllTransformDefinitions() {
        return transformRepository.findAll();
    }

    public boolean CreateTransform(TransformDto transformDto){
        Resource r = defineTransform(transformDto);
        if (!resourceManager.ValidateTransform(r)){
            return false;
        }
        TransformDefinitions pt = new TransformDefinitions();
        pt.setName(transformDto.getName());
        pt.setTransform(Resource.ToString(r));
        transformRepository.save(pt);
        return true;
    }

    public boolean GiveResource(GiveResourceDto giveResourceDto){
        Resource r = new Resource(giveResourceDto.getResourceType(),giveResourceDto.getAmount());
        Map<Agent,Resource> M = new HashMap<>();
        Agent a = agentService.GetAgentByEmail(giveResourceDto.getEmail());
        if(a==null){
            return false;
        }
        M.put(a,r);
        M.put(resourceManager,Resource.mult(r,-1));
        try{
            resourceManager.ApplyTransfer(new Transfer(M));
            return true;
        } catch (TRAException e){
            return false;
        }
    }

    public List<ResourceDto> GetBalanceById(String id){
        Agent a = agentService.GetAgentById(id);
        Resource r = resourceManager.GetBalance(agentService.GetAgentById(id));
        List<ResourceDto> ret = new ArrayList<>();
        for (String key : r.keySet()){
            if (r.get(key)==0){continue;}
            ret.add(new ResourceDto(key,r.get(key)));
        }
        return ret;
    }

    private Resource defineTransform(TransformDto transformDto){
        Resource input = Resource.zero();
        Resource output = Resource.zero();
        for(ResourceDto o : transformDto.getInput()){
            input = Resource.add(input,new Resource(o.getType(),o.getAmount()));
        }
        for(ResourceDto o : transformDto.getOutput()){
            output = Resource.add(output,new Resource(o.getType(),o.getAmount()));
        }
        return Resource.add(Resource.mult(input,-1),output);
    }

    public boolean ApplyTransform(Transformation t){
        return resourceManager.ApplyTransform(t);
    }
}
