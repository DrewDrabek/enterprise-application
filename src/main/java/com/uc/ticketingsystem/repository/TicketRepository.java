package com.uc.ticketingsystem.repository;

import com.uc.ticketingsystem.model.Ticket;
import com.uc.ticketingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


    List<Ticket> findByStatusNot(Ticket.Status status);


    List<Ticket> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndStatusNot(
            String titleKeyword,
            String descriptionKeyword,
            Ticket.Status excludedStatus
    );

}