package com.uc.ticketingsystem.service;

import com.uc.ticketingsystem.dto.TicketDto;
import java.util.List;

public interface TicketService {
        TicketDto createTicket(TicketDto ticketDto, String externalUserId);
        List<TicketDto> getAllTickets();
        TicketDto getTicketById(Long id);
        TicketDto updateTicket(Long id, TicketDto ticketDto);
        TicketDto updateTicketStatus(Long id, String status);
        void deleteTicket(Long id);
}