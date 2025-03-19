// src/main/java/com/uc/ticketingsystem/dto/CommentDto.java
package com.uc.ticketingsystem.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentDto {

    private Long id;

    @NotBlank(message = "Comment text cannot be blank")
    private String commentText;

    private LocalDateTime createdAt; // Might want to include this in the DTO

    private Long ticketId; // Include ticket ID
    private String creatorUserId; // Include user ID

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getCreatorUserId(){
        return creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId){
        this.creatorUserId = creatorUserId;
    }

}