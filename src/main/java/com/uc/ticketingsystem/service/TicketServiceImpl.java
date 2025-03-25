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

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

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

    @Override
    public List<TicketDto> getAllTickets() {
        return ticketRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TicketDto getTicketById(Long id) {
        return ticketRepository.findById(id).map(this::convertToDto).orElse(null);
    }

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

    @Override
    public TicketDto updateTicketStatus(Long id, String status) {
        Ticket existingTicket = ticketRepository.findById(id).orElse(null);
        if (existingTicket == null) {
            return null;
        }

        existingTicket.setStatus(Ticket.Status.valueOf(status));

        return convertToDto(ticketRepository.save(existingTicket));
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

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