package Classes;

import Interfaces.IEvent;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements IEvent {

    private Agent responsible;
    private SimpleDateFormat time;
    private Information body;

    public Agent getResponsible() {
        return responsible;
    }
    public SimpleDateFormat getTime() {
        return time;
    }
    public Information getBody() {
        return body;
    }

    public Event(Agent responsible, Information body) {
        this.responsible = responsible;
        this.time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");;
        this.body = body;
    }
}
