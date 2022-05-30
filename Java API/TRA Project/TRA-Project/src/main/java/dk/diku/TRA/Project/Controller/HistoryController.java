package dk.diku.TRA.Project.Controller;

import dk.diku.TRA.Project.Classes.Event;
import dk.diku.TRA.Project.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    EventService eventService;

    @CrossOrigin
    @GetMapping(path="/getAllEvents")
    public List<Event> GetAllEvents(){
        return eventService.FindAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/getEventsById/{id}", method = RequestMethod.GET)
    public List<Event> GetEventsById(@PathVariable String id){
        return eventService.FindAllByAgentId(id);
    }
}
