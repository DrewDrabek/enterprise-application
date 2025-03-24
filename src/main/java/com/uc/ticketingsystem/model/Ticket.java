package com.uc.ticketingsystem.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;


import java.time.LocalDateTime;


@Entity // Marks this class as a JPA entity (a persistent object)
@Table(name = "tickets") // Specifies the database table name and if you do not do this then JPA will infer based on the class name but better to call it here
public class Ticket {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing ID
    private Long id;

    @NotBlank(message = "Title is mandatory") // Validation: This means that the title cannot be null or empty
    @Size(max = 255, message = "Title cannot exceed 255 characters") // Validation: Limits title length
    @Column(nullable = false)
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters") //Validation: Limits description size
    @Column(length = 1000) // Database constraint: Sets maximum length for description
    private String description;

    @NotNull(message = "Priority is mandatory") // Validation: Priority cannot be null
    @Enumerated(EnumType.STRING) // Store the enum as a string in the database and we are using an enum here because we are going to have specific options and want additional validation
    private Priority priority;

    @NotNull(message = "Status is mandatory")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", nullable = false, updatable = false) // Audit field
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "creator_user_id") // Clearer column name
    private User creatorUser; //  Field name changed

    // Enums
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public enum Status {
        OPEN, IN_PROGRESS, BLOCKED, RESOLVED, CLOSED
    }

    // Lifecycle Callbacks
    @PrePersist  // This method is called before the object is saved in the database
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // These are the getters and setters

    public Long getId() { // this is long retun type and it returns the ID for the entity and this seems to be standard practice
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    // The only other thing that I can think of here that we might need is to add some sort of way to assign a ticket to a user at the object level but I do not know right now - will come back to this after I make the model for the users

}