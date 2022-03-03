package Classes;

import Interfaces.IEvent;
import java.util.Date;

public class Event implements IEvent {

    public Agent responsible;
    public Date time;
    public Information body;

    public Event(Agent responsible, Date time, Information body) {
        this.responsible = responsible;
        this.time = time;
        this.body = body;
    }
}
