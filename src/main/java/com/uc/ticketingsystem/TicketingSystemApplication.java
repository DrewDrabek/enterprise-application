package com.uc.ticketingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

// this exclusion might help with the problem that I am having
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class TicketingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketingSystemApplication.class, args);
    }
}

/*
was able to get the mock data to work and here is what the get all response looks like

[
    {
        "id": 1,
        "title": "Printer Issue",
        "description": "The printer is jammed.",
        "priority": "HIGH",
        "status": "OPEN",
        "creatorUserId": "Mock-User-ID"
    },
    {
        "id": 2,
        "title": "Network Down",
        "description": "Can't access the internet.",
        "priority": "HIGH",
        "status": "IN_PROGRESS",
        "creatorUserId": "Mock-User-ID"
    },
    {
        "id": 3,
        "title": "Software Installation",
        "description": "Need to install new software.",
        "priority": "MEDIUM",
        "status": "OPEN",
        "creatorUserId": "Mock-User-ID"
    }
]

I will work backwards on some of the other items today as well
*/