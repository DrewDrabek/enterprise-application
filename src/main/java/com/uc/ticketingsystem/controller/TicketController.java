package com.uc.ticketingsystem.controller;

import com.uc.ticketingsystem.dto.TicketDto;
import com.uc.ticketingsystem.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@Valid @RequestBody TicketDto ticketDto, @RequestHeader("Authorization") String authHeader) {
        String externalUserId = "mock-user-id"; // In a real application, extract from authHeader
        TicketDto savedTicketDto = ticketService.createTicket(ticketDto, externalUserId);
        return new ResponseEntity<>(savedTicketDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long id) {
        TicketDto ticketDto = ticketService.getTicketById(id);
        if (ticketDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @Valid @RequestBody TicketDto ticketDto) {
        TicketDto updatedTicketDto = ticketService.updateTicket(id, ticketDto);
        if (updatedTicketDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTicketDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TicketDto> updateTicketStatus(@PathVariable Long id, @RequestBody String newStatus) {
        TicketDto updatedTicketDto = ticketService.updateTicketStatus(id, newStatus);
        if (updatedTicketDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTicketDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}