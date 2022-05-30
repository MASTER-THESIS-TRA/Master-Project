package dk.diku.TRA.Project.Services;

import dk.diku.TRA.Project.Classes.ResourceManager;
import dk.diku.TRA.Project.Classes.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    @Autowired
    ResourceManager resourceManager;

    public String transfer(Transfer t){
        if (resourceManager.ApplyTransfer(t)){
            return "Success";
        }
        return "Insufficient funds!";
    }
}
