package com.uc.ticketingsystem.service;

import com.uc.ticketingsystem.dto.TicketDto;

import java.util.List;

public interface TicketService {
        // the idea here is these are the contracts that define what can be done but now how they are done

        // The idea is you define what return type there will be and then the function signature (name and what it takes it)

        /**
         * Create a new ticket
         * @param ticketDto The data for the new ticket
         * @param externalUserId The ID of the user creating the ticket
         * @return The created ticket
         */
        TicketDto createTicket(TicketDto ticketDto, String externalUserId);

        /**
         * Get all tickets
         * @return A list of all tickets
         */
        List<TicketDto> getAllTickets();

        /**
         * Get a ticket by ID
         * @param id The ID of the ticket to get
         * @return The ticket with the given ID
         */
        TicketDto getTicketById(Long id);

        /**
         * Update an existing ticket
         * @param id The ID of the ticket to update
         * @param ticketDto The new data for the ticket
         * @return The updated ticket
         */
        TicketDto updateTicket(Long id, TicketDto ticketDto);

        /**
         * Update the status of an existing ticket
         * @param id The ID of the ticket to update
         * @param status The new status for the ticket
         * @return The updated ticket
         */
        TicketDto updateTicketStatus(Long id, String status);

        /**
         * Delete a ticket
         * @param id The ID of the ticket to delete
         */
        void deleteTicket(Long id);
}