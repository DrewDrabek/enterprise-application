package com.example.kanban.controller;

import com.example.kanban.model.Ticket;
import com.example.kanban.service.TicketService;


public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/board")
    public String showBoard(Model model) {
        model.addAttribute("notStarted", ticketService.getByStatus("Not Started"));
        model.addAttribute("inProgress", ticketService.getByStatus("In Progress"));
        model.addAttribute("done", ticketService.getByStatus("Done"));
        return "board";
    }

    @GetMapping("/ticket/new")
    public String newTicket(@RequestParam String status, Model model) {
        Ticket ticket = new Ticket();
        ticket.setStatus(status);
        model.addAttribute("ticket", ticket);
        return "ticket-form";
    }

    @PostMapping("/ticket/save")
    public String saveTicket(@ModelAttribute Ticket ticket) {
        ticketService.save(ticket);
        return "redirect:/board";
    }

    @GetMapping("/ticket/{id}")
    public String viewTicket(@PathVariable Long id, Model model) {
        Ticket Ticket = ticketService.findById(id);
        model.addAttribute("ticket", ticket);
        return "ticket-detail";
    }
}
