package com.eventtix.config;

import com.eventtix.model.Event;
import com.eventtix.model.Ticket;
import com.eventtix.repository.EventRepository;
import com.eventtix.repository.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(EventRepository eventRepo, TicketRepository ticketRepo) {
        return args -> {
            // Check if the database is already seeded
            if (eventRepo.count() == 0) {
                System.out.println(">> Database is empty. Seeding data...");

                Event musicFest = new Event();
                musicFest.setName("Summer Music Fest");
                musicFest.setLocation("London");
                musicFest.setEventDate(LocalDateTime.now().plusDays(10));
                eventRepo.save(musicFest);

                Ticket t1 = new Ticket();
                t1.setSeatNumber("A1");
                t1.setPrice(new BigDecimal("50.00"));
                t1.setSold(false);
                t1.setEvent(musicFest);
                ticketRepo.save(t1);

                System.out.println(">> Seeding complete.");
            } else {
                System.out.println(">> Database already has data. Skipping seed.");
            }
        };
    }
}