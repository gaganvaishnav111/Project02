package org.revature.RevTaskManagement.controller;

import jakarta.mail.MessagingException;
import org.revature.RevTaskManagement.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public void sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) throws MessagingException {
        emailService.sendEmail(to, subject, body);
    }

    @PostMapping("/sendOtp")
    public void sendOtpEmail(@RequestParam String to, @RequestParam String otp) {
        emailService.sendOtpEmail(to, otp);
    }
}
