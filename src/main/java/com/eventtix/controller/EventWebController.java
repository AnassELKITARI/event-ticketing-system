package com.eventtix.controller;

import com.eventtix.model.Event;
import com.eventtix.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // Note: Not @RestController!
public class EventWebController {

    private final EventService eventService;

    public EventWebController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/events"; // Send people to the events list automatically
    }

    @GetMapping({"/events", "/events/"})
    public String viewEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "event-list"; // This looks for src/main/resources/templates/event-list.html
    }
    @GetMapping("/events/new")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new Event()); // Provide an empty object for the form to bind to
        return "create-event";
    }

    @PostMapping("/events")
    public String createEvent(@ModelAttribute Event event) {
        eventService.saveEvent(event);
        return "redirect:/events"; // Send the user back to the list after saving
    }
}