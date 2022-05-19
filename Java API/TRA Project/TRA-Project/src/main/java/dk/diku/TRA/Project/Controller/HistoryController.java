package dk.diku.TRA.Project.Controller;

import dk.diku.TRA.Project.Classes.Event;
import dk.diku.TRA.Project.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    EventService eventService;

    @GetMapping(path="/getAllEvents")
    public List<Event> GetAllEvents(){
        return eventService.FindAll();
    }

    @GetMapping(path="/getEventsById")
    public List<Event> GetEventsById(String id){
        return eventService.FindAllByAgentId(id);
    }
}
