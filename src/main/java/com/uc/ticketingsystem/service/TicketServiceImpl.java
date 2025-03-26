package com.uc.ticketingsystem.service;

import com.uc.ticketingsystem.dto.TicketDto;
import com.uc.ticketingsystem.model.Ticket;
import com.uc.ticketingsystem.model.User;
import com.uc.ticketingsystem.repository.TicketRepository;
import com.uc.ticketingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the TicketService interface.
 * Provides business logic for managing ticket operations.
 */

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new ticket and associates it with a user.
     * If the user does not exist, a new user is created.
     *
     * @param ticketDto      The TicketDto containing ticket details.
     * @param externalUserId The external user ID of the ticket creator.
     * @return The created TicketDto.
     * WE MIGHT WANT TO CHANGE THIS BUT THIS WAS NEW USER HANDLEING - THIS IS NOT GOOD FOR PRODUCTION AS COULD BE TAKEN ADVANTAGE OF
     */

    @Override
    public TicketDto createTicket(TicketDto ticketDto, String externalUserId) {
        User creator = userRepository.findByExternalUserId(externalUserId);
        if(creator == null){
            creator = new User();
            creator.setExternalUserId(externalUserId);
            creator = userRepository.save(creator);
        }

        Ticket ticket = new Ticket();
        ticket.setTitle(ticketDto.getTitle());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setPriority(Ticket.Priority.valueOf(ticketDto.getPriority()));
        ticket.setStatus(Ticket.Status.valueOf(ticketDto.getStatus()));
        ticket.setCreatorUser(creator);

        ticket = ticketRepository.save(ticket);
        return convertToDto(ticket);
    }

    /**
     * Retrieves all tickets.
     *
     * @return A list of TicketDtos representing all tickets.
     */

    @Override
    public List<TicketDto> getAllTickets() {
        return ticketRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Retrieves a ticket by its ID.
     *
     * @param id The ID of the ticket.
     * @return The TicketDto if found, or null if not found.
     */

    @Override
    public TicketDto getTicketById(Long id) {
        return ticketRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    /**
     * Updates an existing ticket.
     *
     * @param id        The ID of the ticket to update.
     * @param ticketDto The TicketDto containing updated ticket details.
     * @return The updated TicketDto if successful, or null if ticket not found.
     */

    @Override
    public TicketDto updateTicket(Long id, TicketDto ticketDto) {
        Ticket existingTicket = ticketRepository.findById(id).orElse(null);
        if (existingTicket == null) {
            return null;
        }

        existingTicket.setTitle(ticketDto.getTitle());
        existingTicket.setDescription(ticketDto.getDescription());
        existingTicket.setPriority(Ticket.Priority.valueOf(ticketDto.getPriority()));
        existingTicket.setStatus(Ticket.Status.valueOf(ticketDto.getStatus()));

        return convertToDto(ticketRepository.save(existingTicket));
    }

    /**
     * Updates the status of a ticket.
     *
     * @param id     The ID of the ticket to update.
     * @param status The new status of the ticket.
     * @return The updated TicketDto if successful, or null if ticket not found.
     */

    @Override
    public TicketDto updateTicketStatus(Long id, String status) {
        Ticket existingTicket = ticketRepository.findById(id).orElse(null);
        if (existingTicket == null) {
            return null;
        }

        existingTicket.setStatus(Ticket.Status.valueOf(status));

        return convertToDto(ticketRepository.save(existingTicket));
    }

    /**
     * Deletes a ticket by its ID.
     *
     * @param id The ID of the ticket to delete.
     */

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    /**
     * Converts a Ticket entity to a TicketDto.
     *
     * @param ticket The Ticket entity to convert.
     * @return The corresponding TicketDto.
     */

    private TicketDto convertToDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        dto.setId(ticket.getId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setPriority(ticket.getPriority().toString());
        dto.setStatus(ticket.getStatus().toString());
        dto.setCreatorUserId(ticket.getCreatorUser().getExternalUserId());
        return dto;
    }
}