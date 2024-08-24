package org.revature.RevTaskManagement.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    public void testSendEmail() throws MessagingException {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendEmail("test@example.com", "Subject", "Body");

        verify(mailSender, times(1)).send(mimeMessage);
    }

    @Test
    public void testSendOtpEmail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        emailService.sendOtpEmail("test@example.com", "123456");

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
