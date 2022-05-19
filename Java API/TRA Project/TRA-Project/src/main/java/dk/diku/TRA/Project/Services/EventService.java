package dk.diku.TRA.Project.Services;

import dk.diku.TRA.Project.Classes.Event;
import dk.diku.TRA.Project.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public Event RecordEvent(String eventType, String agentUuid, LocalDateTime time, String body){
        Event e = new Event();
        e.setEventType(eventType);
        e.setAgentId(agentUuid);
        e.setTime(time);
        e.setBody(body);
        return eventRepository.save(e);
    }

    public Event FindEvent(String id){
        if(eventRepository.findById(id).isPresent()){
            return eventRepository.findById(id).get();
        }
        return null;
    }

    public List<Event> FindAll(){
        return eventRepository.findAll();
    }
}
