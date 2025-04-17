package com.uc.ticketingsystem.service;

import com.uc.ticketingsystem.model.User;

public interface UserService {
    User findOrCreateUser(String externalUserId);
}