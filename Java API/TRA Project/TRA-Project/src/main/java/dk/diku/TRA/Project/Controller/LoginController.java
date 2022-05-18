package dk.diku.TRA.Project.Controller;


import dk.diku.TRA.Project.Dtos.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {


    @CrossOrigin
    @PostMapping(path = "/intialLogin")
    public UserDto Login(@RequestBody UserDto userDto) {
        return userDto;
    }
}
