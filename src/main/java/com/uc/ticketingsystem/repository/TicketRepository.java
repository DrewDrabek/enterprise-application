package com.uc.ticketingsystem.repository;

import com.uc.ticketingsystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Spring Data JPA provides implementations for save, findAll, findById, deleteById, etc.
    // You can add custom query methods here if needed, for example:
    // List<Ticket> findByPriority(Ticket.Priority priority);
}