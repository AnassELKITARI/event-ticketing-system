package com.eventtix.repository;

import com.eventtix.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Custom method to find all tickets for a specific event
    List<Ticket> findByEventId(Long eventId);
}