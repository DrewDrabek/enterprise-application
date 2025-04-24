package com.uc.ticketingsystem; // Changed package back to your requested package

import com.uc.ticketingsystem.dto.TicketDto;
import com.uc.ticketingsystem.model.Ticket;
import com.uc.ticketingsystem.model.User;
import com.uc.ticketingsystem.repository.TicketRepository;
import com.uc.ticketingsystem.repository.UserRepository;
import com.uc.ticketingsystem.service.TicketServiceImpl; // <-- Explicitly import the service implementation
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; // Required for JUnit 5 with Mockito
import org.mockito.InjectMocks; // Injects mocks into the test subject
import org.mockito.Mock; // Creates mock objects
import org.mockito.junit.jupiter.MockitoExtension; // Integrates Mockito with JUnit 5

import java.util.Arrays;
import java.util.Collections; // Import Collections for singletonList
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*; // Use standard JUnit assertions
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*; // Import all Mockito static methods

// Integrate Mockito with JUnit 5
@ExtendWith(MockitoExtension.class)
// This test class should be located in src/test/java/com/uc/ticketingsystem/TicketServiceImplTest.java
class TicketServiceImplTest {

    // Create a mock instance of TicketRepository
    @Mock
    private TicketRepository ticketRepository;

    // Create a mock instance of UserRepository
    @Mock
    private UserRepository userRepository;

    // Inject the mocks above into an instance of TicketServiceImpl
    // This is the class we are unit testing
    @InjectMocks
    private TicketServiceImpl ticketService; // Injecting the implementation

    private User mockUser;
    private Ticket mockTicketOpen;
    private Ticket mockTicketClosed;
    // DTOs are generally results, less needed as setup unless mocking service itself
    // private TicketDto mockTicketDtoOpen;
    // private TicketDto mockTicketDtoClosed;


    @BeforeEach
    void setUp() {
        // Set up common mock data (Entities that the repository would return)
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setExternalUserId("mock-user-id");

        mockTicketOpen = new Ticket();
        mockTicketOpen.setId(1L);
        mockTicketOpen.setTitle("Test Ticket Open");
        mockTicketOpen.setDescription("Description of test ticket open");
        mockTicketOpen.setPriority(Ticket.Priority.MEDIUM);
        mockTicketOpen.setStatus(Ticket.Status.OPEN);
        mockTicketOpen.setCreatorUser(mockUser); // Associate the user

        mockTicketClosed = new Ticket();
        mockTicketClosed.setId(2L);
        mockTicketClosed.setTitle("Test Ticket Closed");
        mockTicketClosed.setDescription("Description of test ticket closed");
        mockTicketClosed.setPriority(Ticket.Priority.LOW);
        mockTicketClosed.setStatus(Ticket.Status.CLOSED);
        mockTicketClosed.setCreatorUser(mockUser); // Associate the user

        // DTOs are typically the *expected output* after conversion, not needed for repository mock setup
        // mockTicketDtoOpen = ...
        // mockTicketDtoClosed = ...
    }

    // Helper method to convert entity to DTO for assertions (if needed, service does this internally)
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


    // --- Basic Unit Tests for TicketServiceImpl Methods ---

    @Test
    void createTicket_ShouldUseExistingUserAndSaveTicket() {
        // Arrange
        TicketDto newTicketDto = new TicketDto();
        newTicketDto.setTitle("Ticket for Existing User");
        newTicketDto.setPriority("MEDIUM");

        // Mock UserRepository: find returns the existing user
        when(userRepository.findByExternalUserId("existing-external-id")).thenReturn(mockUser);
        // Mock TicketRepository: save returns the mockTicketOpen
        when(ticketRepository.save(any(Ticket.class))).thenReturn(mockTicketOpen);

        // Act
        TicketDto createdTicketDto = ticketService.createTicket(newTicketDto, "existing-external-id");

        // Assert
        verify(userRepository, times(1)).findByExternalUserId("existing-external-id");
        verify(userRepository, never()).save(any(User.class)); // Ensure new user was NOT saved
        verify(ticketRepository, times(1)).save(any(Ticket.class));

        assertNotNull(createdTicketDto);
        assertEquals(mockTicketOpen.getId(), createdTicketDto.getId());
        assertEquals("mock-user-id", createdTicketDto.getCreatorUserId());
    }

    @Test
    void getTicketById_ShouldReturnTicketDto_WhenFound() {
        // Arrange
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(mockTicketOpen));

        // Act
        TicketDto resultDto = ticketService.getTicketById(1L);

        // Assert
        verify(ticketRepository, times(1)).findById(1L);
        assertNotNull(resultDto);
        assertEquals(mockTicketOpen.getId(), resultDto.getId());
    }

    @Test
    void getTicketById_ShouldReturnNull_WhenNotFound() {
        // Arrange
        when(ticketRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        TicketDto resultDto = ticketService.getTicketById(99L);

        // Assert
        verify(ticketRepository, times(1)).findById(99L);
        assertNull(resultDto);
    }

    @Test
    void updateTicket_ShouldUpdateAndReturnTicketDto_WhenFound() {
        // Arrange
        Long ticketIdToUpdate = 1L;
        TicketDto updateDto = new TicketDto();
        updateDto.setTitle("Updated Service Title");
        updateDto.setDescription("Updated Service Description");
        updateDto.setPriority("LOW");
        updateDto.setStatus("RESOLVED");

        // Mock findById to return the existing ticket
        when(ticketRepository.findById(ticketIdToUpdate)).thenReturn(Optional.of(mockTicketOpen)); // Found existing ticket

        // Mock save - return the ticket that was passed into the save method (simulates persistence)
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0));


        // Act
        TicketDto result = ticketService.updateTicket(ticketIdToUpdate, updateDto);

        // Assert
        verify(ticketRepository, times(1)).findById(ticketIdToUpdate);
        verify(ticketRepository, times(1)).save(any(Ticket.class)); // Save should be called

        assertNotNull(result);
        assertEquals(ticketIdToUpdate, result.getId());
        assertEquals(updateDto.getTitle(), result.getTitle());
        assertEquals(updateDto.getDescription(), updateDto.getDescription());
        assertEquals(updateDto.getPriority(), updateDto.getPriority());
        assertEquals(updateDto.getStatus(), updateDto.getStatus());
    }

    @Test
    void updateTicket_ShouldReturnNull_WhenNotFound() {
        // Arrange
        Long ticketIdToUpdate = 99L;
        TicketDto updateDto = new TicketDto();
        updateDto.setTitle("Non Existent");

        when(ticketRepository.findById(ticketIdToUpdate)).thenReturn(Optional.empty());

        // Act
        TicketDto result = ticketService.updateTicket(ticketIdToUpdate, updateDto);

        // Assert
        verify(ticketRepository, times(1)).findById(ticketIdToUpdate);
        verify(ticketRepository, never()).save(any(Ticket.class)); // Save should not be called
        assertNull(result);
    }


    @Test
    void updateTicketStatus_ShouldUpdateStatusAndReturnTicketDto_WhenFoundAndStatusValid() {
        // Arrange
        Long ticketIdToUpdate = 1L;
        String newStatusString = "BLOCKED";

        when(ticketRepository.findById(ticketIdToUpdate)).thenReturn(Optional.of(mockTicketOpen));
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Return the saved ticket

        // Act
        TicketDto result = ticketService.updateTicketStatus(ticketIdToUpdate, newStatusString);

        // Assert
        verify(ticketRepository, times(1)).findById(ticketIdToUpdate);
        verify(ticketRepository, times(1)).save(any(Ticket.class));

        assertNotNull(result);
        assertEquals(ticketIdToUpdate, result.getId());
        assertEquals(newStatusString.toUpperCase(), result.getStatus());
    }

    @Test
    void updateTicketStatus_ShouldReturnNull_WhenNotFound() {
        // Arrange
        Long ticketIdToUpdate = 99L;
        String newStatusString = "BLOCKED";
        when(ticketRepository.findById(ticketIdToUpdate)).thenReturn(Optional.empty());

        // Act
        TicketDto result = ticketService.updateTicketStatus(ticketIdToUpdate, newStatusString);

        // Assert
        verify(ticketRepository, times(1)).findById(ticketIdToUpdate);
        verify(ticketRepository, never()).save(any(Ticket.class));
        assertNull(result);
    }

    @Test
    void updateTicketStatus_ShouldReturnNull_WhenStatusInvalid() {
        // Arrange
        Long ticketIdToUpdate = 1L;
        String invalidStatusString = "INVALID_STATUS";
        when(ticketRepository.findById(ticketIdToUpdate)).thenReturn(Optional.of(mockTicketOpen));
        // No need to mock save

        // Act
        TicketDto result = ticketService.updateTicketStatus(ticketIdToUpdate, invalidStatusString);

        // Assert
        verify(ticketRepository, times(1)).findById(ticketIdToUpdate);
        verify(ticketRepository, never()).save(any(Ticket.class)); // Save should not be called
        assertNull(result);
    }


    @Test
    void deleteTicket_ShouldCallRepositoryDeleteById() {
        // Arrange
        Long ticketIdToDelete = 5L;
        // Mock void method
        doNothing().when(ticketRepository).deleteById(ticketIdToDelete);

        // Act
        ticketService.deleteTicket(ticketIdToDelete);

        // Assert
        verify(ticketRepository, times(1)).deleteById(ticketIdToDelete);
    }

    @Test
    void searchTickets_WithKeyword_ShouldCallSearchRepositoryMethod() {
        // Arrange
        String keyword = "test keyword";
        List<Ticket> searchResults = Collections.singletonList(mockTicketOpen);

        when(ticketRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndStatusNot(
                eq(keyword), eq(keyword), eq(Ticket.Status.CLOSED)
        )).thenReturn(searchResults);


        // Act
        List<TicketDto> result = ticketService.searchTickets(keyword);

        // Assert
        verify(ticketRepository, times(1))
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndStatusNot(
                        eq(keyword), eq(keyword), eq(Ticket.Status.CLOSED)
                );
        verify(ticketRepository, never()).findByStatusNot(any());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockTicketOpen.getId(), result.get(0).getId());
    }

    @Test
    void searchTickets_WithoutKeyword_ShouldCallGetAllNonClosedRepositoryMethod() {
        // Arrange
        String keyword = "";
        List<Ticket> nonClosedTickets = Collections.singletonList(mockTicketOpen);

        when(ticketRepository.findByStatusNot(Ticket.Status.CLOSED)).thenReturn(nonClosedTickets);

        // Act
        List<TicketDto> result = ticketService.searchTickets(keyword);

        // Assert
        verify(ticketRepository, times(1)).findByStatusNot(Ticket.Status.CLOSED);
        verify(ticketRepository, never())
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndStatusNot(
                        anyString(), anyString(), any()
                );

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockTicketOpen.getId(), result.get(0).getId());
    }
}
