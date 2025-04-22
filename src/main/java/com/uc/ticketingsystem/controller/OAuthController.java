package com.uc.ticketingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuthController {

    @GetMapping("/login")
    public String login() {
        // This will trigger the OAuth login flow
        return "redirect:/login/oauth2";
    }
}
