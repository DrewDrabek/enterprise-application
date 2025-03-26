package com.uc.ticketingsystem.service;

import com.uc.ticketingsystem.dto.TicketDto;
import com.uc.ticketingsystem.model.Ticket;
import com.uc.ticketingsystem.model.User;
import com.uc.ticketingsystem.repository.TicketRepository;
import com.uc.ticketingsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private TicketDto ticketDto;
    private Ticket ticket;
    private User user;

    @BeforeEach
    public void setUp() {
        ticketDto = new TicketDto();
        ticketDto.setTitle("Test Ticket");
        ticketDto.setDescription("Test Description");
        ticketDto.setPriority("HIGH");
        ticketDto.setStatus("OPEN");

        user = new User();
        user.setId(1L);
        user.setExternalUserId("test-user-id");

        ticket = new Ticket();
        ticket.setId(1L);
        ticket.setTitle("Test Ticket");
        ticket.setDescription("Test Description");
        ticket.setPriority(Ticket.Priority.HIGH);
        ticket.setStatus(Ticket.Status.OPEN);
        ticket.setCreatorUser(user);
        ticket.setCreatedAt(LocalDateTime.now());
    }

    @Test
    public void createTicket_ShouldCreateAndReturnTicketDto() {
        when(userRepository.findByExternalUserId("test-user-id")).thenReturn(user);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        TicketDto result = ticketService.createTicket(ticketDto, "test-user-id");

        assertNotNull(result);
        assertEquals(ticketDto.getTitle(), result.getTitle());
        assertEquals(ticketDto.getDescription(), result.getDescription());
        assertEquals(ticketDto.getPriority(), result.getPriority());
        assertEquals(ticketDto.getStatus(), result.getStatus());
        assertEquals(user.getExternalUserId(), result.getCreatorUserId());

        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    public void createTicket_ShouldCreateUserIfNotFound() {
        when(userRepository.findByExternalUserId("new-user-id")).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        TicketDto result = ticketService.createTicket(ticketDto, "new-user-id");

        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void getAllTickets_ShouldReturnListOfTicketDtos() {
        when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket));

        List<TicketDto> result = ticketService.getAllTickets();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ticketDto.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void getTicketById_ShouldReturnTicketDtoIfExists() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        TicketDto result = ticketService.getTicketById(1L);

        assertNotNull(result);
        assertEquals(ticketDto.getTitle(), result.getTitle());
    }

    @Test
    public void getTicketById_ShouldReturnNullIfNotFound() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        TicketDto result = ticketService.getTicketById(1L);

        assertNull(result);
    }

    @Test
    public void updateTicket_ShouldUpdateAndReturnTicketDto() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        TicketDto result = ticketService.updateTicket(1L, ticketDto);

        assertNotNull(result);
        assertEquals(ticketDto.getTitle(), result.getTitle());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    public void updateTicket_ShouldReturnNullIfTicketNotFound() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        TicketDto result = ticketService.updateTicket(1L, ticketDto);

        assertNull(result);
    }

    @Test
    public void updateTicketStatus_ShouldUpdateAndReturnTicketDto() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        TicketDto result = ticketService.updateTicketStatus(1L, "CLOSED");

        assertNotNull(result);
        assertEquals("CLOSED", result.getStatus());
    }

    @Test
    public void updateTicketStatus_ShouldReturnNullIfTicketNotFound() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        TicketDto result = ticketService.updateTicketStatus(1L, "CLOSED");

        assertNull(result);
    }

    @Test
    public void deleteTicket_ShouldDeleteTicket() {
        ticketService.deleteTicket(1L);
        verify(ticketRepository, times(1)).deleteById(1L);
    }
}