package com.uc.ticketingsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

// adding the active profiles so that I do the mock classes here

@SpringBootTest
@ActiveProfiles("mock")
class TicketingSystemApplicationTests {

    @Test
    void contextLoads() {
    }

}
