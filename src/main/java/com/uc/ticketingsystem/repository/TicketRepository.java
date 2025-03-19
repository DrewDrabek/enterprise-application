package com.uc.ticketingsystem.repository;

import com.uc.ticketingsystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional; // Import Optional
import java.util.List;


// this is a format for the repository item this will need to be changed

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Procedure(procedureName = "CreateTicket")
    Long createTicket(@Param("p_title") String title,
                      @Param("p_description") String description,
                      @Param("p_priority") String priority,
                      @Param("p_status") String status,
                      @Param("p_creatorUserId") String creatorUserId);

    @Procedure(procedureName = "GetAllTickets")
    List<Ticket> getAllTickets();

    @Procedure(procedureName = "GetTicketById")
    Optional<Ticket> getTicketById(@Param("p_ticketId") Long ticketId); // Use Optional

    @Procedure(procedureName = "UpdateTicket")
    void updateTicket(@Param("p_ticketId") Long ticketId,
                      @Param("p_title") String title,
                      @Param("p_description") String description,
                      @Param("p_priority") String priority,
                      @Param("p_status") String status);

    @Procedure(procedureName = "UpdateTicketStatus")
    void updateTicketStatus(@Param("p_ticketId") Long ticketId, @Param("p_status") String status);

    @Procedure(procedureName = "DeleteTicket")
    void deleteTicket(@Param("p_ticketId") Long ticketId);
}