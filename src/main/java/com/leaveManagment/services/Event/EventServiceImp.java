package com.leaveManagment.services.Event;

import com.leaveManagment.entities.Event;
import com.leaveManagment.entities.Team;
import com.leaveManagment.repositories.EventRepository;
import com.leaveManagment.repositories.TeamRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImp implements IEventService {

    private final EventRepository eventRepository;
    private final TeamRepository teamRepository;

    @Override
    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event addEvent(Event e) {
        return eventRepository.save(e);
    }

    @Override
    public Event updateEvent(Event e) {
        return eventRepository.save(e);
    }

    @Override
    public Event retrieveEvent(Integer idEvent) {
        return eventRepository.findById(idEvent).orElse(null);
    }

    @Override
    @Transactional
    public void archiveEvent(Integer idEvent) {
        Event event = eventRepository.findById(idEvent).orElse(null);
        Assert.notNull(event,"Event ne doit pas etre null");
        event.setArchive(true);
        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void affectEventToTeam(Integer idEvent, Integer idTeam) {
        Event event = eventRepository.findById(idEvent).orElse(null);
        Team team = teamRepository.findById(idTeam).orElse(null);
        Assert.notNull(event,"Event ne doit pas etre null");
        Assert.notNull(team,"Team ne doit pas etre null");
        event.setTeam(team);
        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void desaffectEventFromTeam(Integer idEvent) {
        Event event = eventRepository.findById(idEvent).orElse(null);
        Assert.notNull(event,"Event ne doit pas etre null");
        event.setTeam(null);
        eventRepository.save(event);
    }

    @Override
    public List<Event> getEventByTeam(Integer idTeam) {
        return eventRepository.findByTeam_Id(idTeam);
    }
}
