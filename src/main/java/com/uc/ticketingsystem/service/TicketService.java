package enterprise.uc.ticketingsystem.service;

import enterprise.uc.ticketingsystem.model.Ticket;

import java.util.List;

public interface TicketService  {

        Ticket createTicket(Ticket ticket);

        Ticket getTicketById(Long id);

        List<Ticket> getAllTickets();

        List<Ticket> getActiveTickets();

        Ticket updateTicketStatus(Long id, String status);

        void deleteTicket(Long id);
    }