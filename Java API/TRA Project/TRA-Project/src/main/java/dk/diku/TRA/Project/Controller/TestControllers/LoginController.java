package dk.diku.TRA.Project.controller.TestControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(path = "/login")
public class LoginController {
    @GetMapping("/")
    public String index (){
        return "Welcome!";
    }
}
