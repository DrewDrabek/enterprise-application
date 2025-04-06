package com.example.kanban.model;

public class Ticket {
    private Long id;
    private String title;
    private String description;
    private String status; // "Not Started", "In Progress", "Done"
    private String otherInfo;

    public Ticket() {}

    public Ticket(Long id, String title, String description, String status, String otherInfo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.otherInfo = otherInfo;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getOtherInfo() { return otherInfo; }
    public void setOtherInfo(String otherInfo) { this.otherInfo = otherInfo; }
}
