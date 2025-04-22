package com.uc.ticketingsystem.service;

import com.uc.ticketingsystem.dto.TicketDto;
import java.util.List;

public interface TicketService {
        TicketDto createTicket(TicketDto ticketDto, String externalUserId);
        // This method will now implicitly return only non-closed tickets due to implementation change
        List<TicketDto> getAllTickets();
        TicketDto getTicketById(Long id); // Assuming this should still return closed tickets if accessed directly
        TicketDto updateTicket(Long id, TicketDto ticketDto);
        TicketDto updateTicketStatus(Long id, String status);
        void deleteTicket(Long id);

        List<TicketDto> searchTickets(String keyword);
}