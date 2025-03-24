package com.uc.ticketingsystem.service;

import com.uc.ticketingsystem.model.User;

public interface UserService {
    /**
     * Find a user by their external ID, or create a new user if no user with the specified ID exists
     * @param externalUserId The ID of the user
     * @return The found or newly created user
     */
    User findOrCreateUser(String externalUserId);
    // We really might not even need this it really will just depend on how we manage users - something that we will need to talk about this weekend
}