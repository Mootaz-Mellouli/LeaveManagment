package com.leaveManagment.controllers;

import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.Event;
import com.leaveManagment.services.Event.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@CrossOrigin
public class EventController {
    private final IEventService eventService;
    @GetMapping()
    public List<Event> retrieveAllEvents (){
        return eventService.retrieveAllEvents();
    }

    @GetMapping("/{eventid}")
    public Event retrieveEvent(@PathVariable("eventid") Integer eventId) {
        return eventService.retrieveEvent(eventId);
    }

    @PostMapping()
    public void addEvent(@RequestBody Event event) {

        eventService.addEvent(event);
    }

    @PutMapping()
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/{eventid}")
    public void removeEvent(@PathVariable("eventid") Integer eventId) {
        eventService.deleteEvent(eventId);
    }

    @PutMapping("/{eventid}/{teamid}")
    public void affectEventToTeam(@PathVariable("eventid") Integer eventid,@PathVariable("teamid") Integer teamid) {
         eventService.affectEventToTeam(eventid,teamid);
    }
    @PutMapping("/desaffectEventToTeam/{eventid}")
    public void desaffectEventToTeam(@PathVariable("eventid") Integer eventid) {
        eventService.desaffectEventFromTeam(eventid);
    }
    @GetMapping("/retrieveEventByTeam/{teamid}")
    public List<Event> retrieveEventByTeam(@PathVariable("teamid") Integer teamid) {
        return eventService.getEventByTeam(teamid);
    }
}