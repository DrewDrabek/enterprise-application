package enterprise.uc.ticketingsystem.model;

public class Ticket {

    private Long id;             // Unique identifier for the ticket
    private String title;        // Title of the ticket
    private String description;  // Detailed description of the issue
    private String status;       // Current status (e.g., "Open", "In Progress", "Resolved")
    private String priority;     // Priority level (e.g., "High", "Medium", "Low")
    // Add any other relevant fields like:
    // private User assignedTo;    // Employee assigned to the ticket
    // private Date createdAt;     // Date and time the ticket was created

    // Constructor(s)
    public Ticket() {
        // Default constructor
    }

    public Ticket(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = "Open"; // Default status when a ticket is created
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    // Additional methods if we need them
}