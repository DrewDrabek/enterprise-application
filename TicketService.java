package com.example.kanban.service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@com.example.kanban.service.Service
public class TicketService {
    private final Map<Long, Ticket> tickets = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public TicketService() {
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets.values());
    }

    public List<Ticket> getByStatus(String status) {
        return tickets.values().stream()
                .filter(t -> t.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public Ticket findById(Long id) {
        return tickets.get(id);
    }

    public void save(Ticket ticket) {
        if (ticket.getId() == null) {
            ticket.setId(idGenerator.incrementAndGet());
        }
        tickets.put(ticket.getId(), ticket);
    }
}
