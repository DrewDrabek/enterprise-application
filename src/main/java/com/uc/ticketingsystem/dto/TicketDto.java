package com.uc.ticketingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// This is my first go at this - we also should not need the user dto since there we are going to use the third party auth

@Data
public class TicketDto {
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotBlank(message = "Priority is mandatory")
    private String priority;

    @NotBlank(message = "Status is mandatory")
    private String status;

    private String creatorUserId;
}