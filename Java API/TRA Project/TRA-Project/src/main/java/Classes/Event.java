package Classes;

import Interfaces.IEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class Event implements IEvent {

    public String eventName;
    private List<Agent> responsible;
    private SimpleDateFormat time;
    private Information body;

    public Event(String eventName, List<Agent> responsible, Information body) {
        this.eventName = eventName;
        this.responsible = responsible;
        this.time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");;
        this.body = body;
    }

    public List<Agent> getResponsible() {
        return responsible;
    }
    public SimpleDateFormat getTime() {
        return time;
    }
    public Information getBody() {
        return body;
    }
}
