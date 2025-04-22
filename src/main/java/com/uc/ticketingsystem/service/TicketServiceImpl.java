package com.uc.ticketingsystem.service;

import com.uc.ticketingsystem.dto.TicketDto;
import com.uc.ticketingsystem.model.Ticket;
import com.uc.ticketingsystem.model.User;
import com.uc.ticketingsystem.repository.TicketRepository;
import com.uc.ticketingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    // createTicket method remains the same

    @Override
    public TicketDto createTicket(TicketDto ticketDto, String externalUserId) {
        User creator = userRepository.findByExternalUserId(externalUserId);
        if (creator == null) {
            creator = new User();
            creator.setExternalUserId(externalUserId);
            creator = userRepository.save(creator);
        }

        Ticket ticket = new Ticket();
        ticket.setTitle(ticketDto.getTitle());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setPriority(Ticket.Priority.valueOf(ticketDto.getPriority().toUpperCase()));
        // Default new tickets to OPEN, or let DTO decide if needed
        ticket.setStatus(Ticket.Status.OPEN);
        ticket.setCreatorUser(creator);

        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToDto(savedTicket);
    }


    @Override
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findByStatusNot(Ticket.Status.CLOSED);
        return tickets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto getTicketById(Long id) {
        // (This method remains the same - no filtering by status here)
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        return ticketOptional.map(this::convertToDto).orElse(null);
    }

    @Override
    public TicketDto updateTicket(Long id, TicketDto ticketDto) {
        Optional<Ticket> existingTicketOptional = ticketRepository.findById(id);
        if (existingTicketOptional.isPresent()) {
            Ticket existingTicket = existingTicketOptional.get();
            existingTicket.setTitle(ticketDto.getTitle());
            existingTicket.setDescription(ticketDto.getDescription());
            existingTicket.setPriority(Ticket.Priority.valueOf(ticketDto.getPriority().toUpperCase()));
            existingTicket.setStatus(Ticket.Status.valueOf(ticketDto.getStatus().toUpperCase()));
            Ticket updatedTicket = ticketRepository.save(existingTicket);
            return convertToDto(updatedTicket);
        }
        return null;
    }

    @Override
    public TicketDto updateTicketStatus(Long id, String status) {
        Optional<Ticket> existingTicketOptional = ticketRepository.findById(id);
        if (existingTicketOptional.isPresent()) {
            Ticket existingTicket = existingTicketOptional.get();
            try {
                existingTicket.setStatus(Ticket.Status.valueOf(status.toUpperCase()));
                Ticket updatedTicket = ticketRepository.save(existingTicket);
                return convertToDto(updatedTicket);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid status value provided: " + status);
                return null;
            }
        }
        return null;
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public List<TicketDto> searchTickets(String keyword) {
        List<Ticket> tickets;
        if (keyword == null || keyword.trim().isEmpty()) {
            // If no keyword, return all non-closed tickets
            tickets = ticketRepository.findByStatusNot(Ticket.Status.CLOSED);
        } else {
            // Use the new repository method to search title/description and exclude CLOSED
            String searchKeyword = keyword.trim();
            tickets = ticketRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndStatusNot(
                    searchKeyword,
                    searchKeyword,
                    Ticket.Status.CLOSED
            );
        }
        // Map results to DTOs
        return tickets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TicketDto convertToDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        dto.setId(ticket.getId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setPriority(ticket.getPriority().toString());
        dto.setStatus(ticket.getStatus().toString());
        if (ticket.getCreatorUser() != null) {
            dto.setCreatorUserId(ticket.getCreatorUser().getExternalUserId());
        }

        return dto;
    }
}