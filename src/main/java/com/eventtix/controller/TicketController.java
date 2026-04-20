package com.eventtix.controller;

import com.eventtix.model.Ticket;
import com.eventtix.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PutMapping("/{id}/book")
    public ResponseEntity<?> bookTicket(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ticketService.bookTicket(id));
        } catch (RuntimeException e) {
            // Now we return the actual message: "This ticket is already sold!"
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Ticket> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.cancelBooking(id));
    }
}