package com.uc.ticketingsystem.controller;

import com.uc.ticketingsystem.dto.TicketDto;
import com.uc.ticketingsystem.model.Ticket;
import com.uc.ticketingsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.List;

@Controller
public class TicketWebController {

    @Autowired
    private TicketService ticketService;

    /**
     * Displays the main ticket list page.
     * Handles optional search by keyword and automatically filters out CLOSED tickets.
     *
     * @param model   The Spring UI model.
     * @param keyword Optional search keyword from the request parameter.
     * @return The name of the Thymeleaf template ("tickets").
     */
    @GetMapping("/tickets")
    public String showTicketsPage(Model model, @RequestParam(name = "keyword", required = false) String keyword) {

        List<TicketDto> tickets;

        // Use searchTickets if keyword is present, otherwise getAllTickets
        if (keyword != null && !keyword.trim().isEmpty()) {
            System.out.println("Searching with keyword: " + keyword);
            tickets = ticketService.searchTickets(keyword); // Use the search service method
        } else {
            // If no keyword, get all non-closed tickets
            tickets = ticketService.getAllTickets();
        }

        // Add the list of tickets (filtered/searched, excluding CLOSED) to the model
        model.addAttribute("tickets", tickets);
        // Add the keyword back to the model for the search box
        model.addAttribute("keyword", keyword);
        // Add all possible status values to the model for the status update dropdown
        model.addAttribute("allStatuses", Ticket.Status.values());

        // Return the name of the Thymeleaf template file (without .html)
        return "tickets"; // Renders src/main/resources/templates/tickets.html
    }

    /**
     * Handles the submission from the mini status update form on the ticket list page.
     *
     * @param id        The ID of the ticket to update (from path variable).
     * @param newStatus The new status selected in the dropdown (from request parameter).
     * @return A redirect instruction back to the ticket list page.
     */
    @PostMapping("/tickets/{id}/update-status")
    public String updateTicketStatus(@PathVariable Long id, @RequestParam String newStatus) {
        // Call the existing service method (used by your PATCH API)
        // Consider adding error handling here (e.g., what if update fails?)
        ticketService.updateTicketStatus(id, newStatus);
        // Redirect back to the ticket list page to see the change
        return "redirect:/tickets";
    }

    /**
     * Redirects requests to the root path ("/") to the main ticket list page ("/tickets").
     *
     * @return A redirect instruction.
     */
    @GetMapping("/")
    public String redirectToTickets() {
        return "redirect:/tickets";
    }

}