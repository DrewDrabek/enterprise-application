// src/main/java/com/uc/ticketingsystem/service/UserService.java
package com.uc.ticketingsystem.service;

import com.uc.ticketingsystem.model.User;

public interface UserService {
    User findOrCreateUser(String externalUserId);
    // We really might not even need this it really will just depend on how we manage users - something that we will need to talk about this weekend
}