package dk.diku.TRA.Project.Controller;


import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Classes.Resource;
import dk.diku.TRA.Project.Classes.Transfer;
import dk.diku.TRA.Project.Dtos.TransferDto;
import dk.diku.TRA.Project.Exceptions.TRAException;
import dk.diku.TRA.Project.Services.AgentService;
import dk.diku.TRA.Project.Services.EventService;
import dk.diku.TRA.Project.Services.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    TransferService transferService;
    @Autowired
    AgentService agentService;
    @Autowired
    EventService eventService;

    @CrossOrigin
    @PostMapping(path = "/createTransfer")
    public String createTransfer(@RequestBody TransferDto transferDto) {
        Transfer t = makeTransfer(transferDto);
        if(t.equals(Transfer.zero())){
            return "Unexpected error";
        }
        String transferResult = transferService.transfer(t);
        if (transferResult.equals("Success")){
            eventService.RecordEvent("Transfer",transferDto.sender, LocalDateTime.now(),Transfer.ToString(t));
        }
        return transferResult;
    }


    private Transfer makeTransfer(TransferDto transferDto){
        Map<Agent, Resource> M = new HashMap<>();
        M.put(agentService.GetAgentById(transferDto.sender),
                new Resource(transferDto.resourceName, -transferDto.amount));
        M.put(agentService.GetAgentByEmail(transferDto.receiver),
                new Resource(transferDto.resourceName, transferDto.amount));
        try{
            return new Transfer(M);
        }catch(TRAException e){
            return Transfer.zero();
        }
    }
}
