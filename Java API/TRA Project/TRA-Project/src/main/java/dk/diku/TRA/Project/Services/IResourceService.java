package dk.diku.TRA.Project.Services;

import dk.diku.TRA.Project.Classes.ResourceManager;
import dk.diku.TRA.Project.Classes.Transfer;

public interface IResourceService {
    boolean SellResource(ResourceManager resourceManager, Transfer transfer);
    boolean BuyResource(ResourceManager resourceManager, Transfer transfer);
}
