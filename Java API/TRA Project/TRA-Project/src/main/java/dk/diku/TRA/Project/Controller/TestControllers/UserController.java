package dk.diku.TRA.Project.controller.TestControllers;

import Models.Transfer.ResourceDto;
import Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    //private final UserService userService;

    @CrossOrigin
    @GetMapping(path = "/getUserInfo")
    public @ResponseBody String getUserInfo() {
        JSONObject userData = new JSONObject();
        userData.put("id", UUID.randomUUID());
        userData.put("name", "Alexander Borgert");
        userData.put("balance", new Random().nextInt(1333337));
        userData.put("pendingTransactions", 4);
        return userData.toString();
    }

    @CrossOrigin
    @GetMapping(path = "/test")
    public String test() {
        return "Success!";
    }
}
