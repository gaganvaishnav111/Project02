package org.revature.RevTaskManagement.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/setSession")
    public String setSession(HttpSession session) {
        session.setAttribute("username", "JohnDoe");
        return "Session attribute set";
    }

    @GetMapping("/getSession")
    public String getSession(HttpSession session) {
        return "Username: " + session.getAttribute("username");
    }
}
