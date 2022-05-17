package dk.diku.TRA.Project.controller.TestControllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;



public class ResponseHandler {
    /**
     * @param message       The response message
     * @param status        Http status of the response
     * @param responseObj   The body of the response
     * @return              Returns a spring ResponseEntity<Obj>
     */
    public static ResponseEntity generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity(map,status);
    }
}