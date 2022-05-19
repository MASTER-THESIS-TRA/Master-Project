package dk.diku.TRA.Project.Controller;


import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Dtos.AgentDto;
import dk.diku.TRA.Project.Services.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/agent")
public class AgentController {
    //Delete class??
}