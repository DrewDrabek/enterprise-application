package com.uc.ticketingsystem.repository;

import com.uc.ticketingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByExternalUserId(String externalUserId);
}