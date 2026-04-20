package com.eventtix.service;

import com.eventtix.model.Ticket;
import com.eventtix.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public Ticket bookTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.isSold()) {
            throw new RuntimeException("This ticket is already sold!");
        }

        ticket.setSold(true);
        return ticketRepository.save(ticket);
    }

    public Ticket cancelBooking(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        // Logic: Set sold to false to "release" it
        ticket.setSold(false);
        return ticketRepository.save(ticket);
    }
}