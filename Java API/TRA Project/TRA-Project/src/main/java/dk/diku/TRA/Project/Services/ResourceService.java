package dk.diku.TRA.Project.Services;

import dk.diku.TRA.Project.Classes.Resource;
import dk.diku.TRA.Project.Classes.ResourceManager;
import dk.diku.TRA.Project.Classes.Transfer;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ResourceService {


    public boolean SellResource(ResourceManager resourceManager, Transfer transfer) {
        throw new NotImplementedException();
    }

    public boolean BuyResource(ResourceManager resourceManager, Transfer transfer) {
        throw new NotImplementedException();
    }

    public List<Resource> GetAllResources() {
        List<Resource> resources = new ArrayList<>();
        resources.add(new Resource("Coffee", 10));
        return resources;
    }
}
