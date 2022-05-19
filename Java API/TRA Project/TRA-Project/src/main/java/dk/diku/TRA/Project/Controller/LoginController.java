package dk.diku.TRA.Project.Controller;


import dk.diku.TRA.Project.Dtos.UserDto;
import dk.diku.TRA.Project.Services.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AgentService agentService;


    @CrossOrigin
    @PostMapping(path="/validateLogin")
    public String ValidateLogin(@RequestBody UserDto userDto){
        System.out.print("Email: " + userDto.email + " Password: " + userDto.password);
        return agentService.validateLogin(userDto.email, userDto.password);
    }
}
