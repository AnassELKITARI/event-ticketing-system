package com.eventtix.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    private BigDecimal price;

    private boolean isSold = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;

    /**
     * OPTIMISTIC LOCKING:
     * This field is the "secret sauce" for your project.
     * Hibernate uses this to ensure that if two users try to buy
     * the same ticket, only one wins.
     */
    @Version
    private Long version;
}