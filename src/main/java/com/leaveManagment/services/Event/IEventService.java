package com.leaveManagment.services.Event;

import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.Event;

import java.util.List;

public interface IEventService {
    List<Event> retrieveAllEvents();

    Event addEvent(Event t);

    Event updateEvent (Event t);

    Event retrieveEvent (Integer idEEvent);

    void deleteEvent(Integer idEvent);
    void affectEventToTeam(Integer idEvent,Integer idTeam);
    void desaffectEventFromTeam(Integer idEvent);
    List<Event> getEventByTeam(Integer idTeam);
}
