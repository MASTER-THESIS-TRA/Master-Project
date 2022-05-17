package Services;


import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@Component
public class UserService {
    public UUID uuid;

    public UserService() {
        this.uuid = UUID.randomUUID();
    }

    public JSONObject GenerateUserData() {
        JSONObject userData = new JSONObject();
        userData.put("id", uuid);
        userData.put("balance", new Random());
        userData.put("pendingTransactions", 4);
        return userData;
    }
}
