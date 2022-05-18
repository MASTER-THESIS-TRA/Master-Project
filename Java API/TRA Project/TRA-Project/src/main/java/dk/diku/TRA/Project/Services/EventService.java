package dk.diku.TRA.Project.Services;

import dk.diku.TRA.Project.Classes.Event;
import dk.diku.TRA.Project.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class EventService {
    @Autowired
    EventRepository eventRepository;

    public Event recordEvent(String eventType, String agentUuid, LocalDateTime time, String body){
        Event e = new Event();
        e.setEventType(eventType);
        e.setAgentId(agentUuid);
        e.setTime(time);
        e.setBody(body);
        return eventRepository.save(e);
    }

    public Event findEvent(String id){
        if(eventRepository.findById(id).isPresent()){
            return eventRepository.findById(id).get();
        }
        return null;
    }
}
