package com.uc.ticketingsystem.service;

import com.uc.ticketingsystem.dto.TicketDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// NOTES THIS IS TEMPORARY THIS IS NOT SOMETHING THAT I AM GOING TO USER LONG TERM IT IS JUST THERE SO THAT I CAN FIX THE MOCK TESTING

@Service
@Profile("mock") // this tell spring to only create this bean when the mock profile is active
public class MockTicketServiceImpl implements TicketService {
        private List<TicketDto> mockTickets = new ArrayList<>(List.of(
                createMockTicket(1L, "Printer Issue", "The printer is jammed.", "HIGH", "OPEN"),
                createMockTicket(2L, "Network Down", "Can't access the internet.", "HIGH", "IN_PROGRESS"),
                createMockTicket(3L, "Software Installation", "Need to install new software.", "MEDIUM", "OPEN")
        ));

        private TicketDto createMockTicket(Long id, String title, String description, String priority, String status) {
                TicketDto ticketDto = new TicketDto();
                ticketDto.setId(id);
                ticketDto.setTitle(title);
                ticketDto.setDescription(description);
                ticketDto.setPriority(priority);
                ticketDto.setStatus(status);
                ticketDto.setCreatorUserId("Mock-User-ID"); // For consistency
                return ticketDto;
        }

        @Override
        public TicketDto createTicket(TicketDto ticketDto, String externalUserId) {
                // Simulate creating a new ticket (assign a new ID)
                long newId = mockTickets.size() + 1;
                ticketDto.setId(newId);
                ticketDto.setCreatorUserId(externalUserId);
                mockTickets.add(ticketDto);
                return ticketDto;
        }

        @Override
        public List<TicketDto> getAllTickets() {
                return mockTickets;
        }

        @Override
        public TicketDto getTicketById(Long id) {
                return mockTickets.stream()
                        .filter(t -> t.getId().equals(id))
                        .findFirst()
                        .orElse(null); // Return null if not found
        }
        @Override
        public TicketDto updateTicketStatus(Long id, String status) {
                for (TicketDto ticket : mockTickets) {
                        if (ticket.getId().equals(id)) {
                                ticket.setStatus(status);
                                return ticket;
                        }
                }
                return null; // Or throw exception
        }

        @Override
        public TicketDto updateTicket(Long id, TicketDto ticketDto) {
                TicketDto existing = getTicketById(id);
                if (existing != null) {
                        existing.setTitle(ticketDto.getTitle());
                        existing.setDescription(ticketDto.getDescription());
                        existing.setStatus(ticketDto.getStatus());
                        existing.setPriority(ticketDto.getPriority());
                        return existing;
                }
                return null;
        }

        @Override
        public void deleteTicket(Long id) {
                mockTickets.removeIf(ticket -> ticket.getId().equals(id));
        }
}