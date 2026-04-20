package com.eventtix.service;

import com.eventtix.model.Event;
import com.eventtix.model.Ticket;
import com.eventtix.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Transactional
    public Event saveEvent(Event event) {
        if (event.getInitialTicketCount() != null && event.getInitialTicketCount() > 0) {
            for (int i = 1; i <= event.getInitialTicketCount(); i++) {
                Ticket ticket = new Ticket();
                ticket.setSeatNumber("Seat-" + i);
                ticket.setPrice(new BigDecimal("50.00"));
                ticket.setSold(false);
                ticket.setEvent(event);
                event.getTickets().add(ticket);
            }
        }
        return eventRepository.save(event);
    }
}