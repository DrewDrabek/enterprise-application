package enterprise.uc.ticketingsystem.service;

import enterprise.uc.ticketingsystem.model.Ticket;

import com.uc.ticketingsystem.dto.TicketDto;

import java.util.List;

public interface TicketService {

        // the idea here is these are the contracts that define what can be done but now how they are done

        // The idea is you define what return type there will be and then the function signature (name and what it takes it)

        TicketDto createTicket(TicketDto ticketDto, String externalUserId); //these are expected to return a ticket dto class we will need to make these

        List<TicketDto> getAllTickets();

        TicketDto getTicketById(Long id);

        TicketDto updateTicket(Long id, TicketDto ticketDto); //for updating entire ticket

        TicketDto updateTicketStatus(Long id, String status); //For just updating the status

        void deleteTicket(Long id);
}