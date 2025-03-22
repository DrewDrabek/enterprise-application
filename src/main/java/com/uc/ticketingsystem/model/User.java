package com.uc.ticketingsystem.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Users")
public class User {

   @Id
   //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Internal ID

   // @NotBlank(message = "External User ID is mandatory")
    //@Column(name = "external_user_id", nullable = false, unique = true)
    private String externalUserId; // ID from the identity provider (GUID)

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }
}


// This might need to change a little as this is for the users later on as I will need to think through more how we are going to be using the user id but for now this is fine