package org.revature.RevTaskManagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.revature.RevTaskManagement.models.Otp;
import org.revature.RevTaskManagement.repository.OtpRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OtpServiceTest {

    @Mock
    private OtpRepository otpRepository;

    @InjectMocks
    private OtpService otpService;

    @Test
    public void testGenerateOtp() {
        String otp = otpService.generateOtp();
        assertTrue(otp.matches("\\d{6}")); // Check if OTP is a 6-digit number
    }

//    @Test
//    public void testSaveOtpForUser() {
//        Otp otp = new Otp(1, "123456");
//        when(otpRepository.save(otp)).thenReturn(otp);
//        otpService.saveOtpForUser(1, "123456");
//        verify(otpRepository, times(1)).save(otp);
//    }

    @Test
    public void testValidateOtpForUser() {
        Otp otp = new Otp(1, "123456");
        when(otpRepository.findByUserId(1)).thenReturn(otp);

        boolean isValid = otpService.validateOtpForUser(1, "123456");
        assertTrue(isValid);

        boolean isInvalid = otpService.validateOtpForUser(1, "654321");
        assertFalse(isInvalid);
    }

    @Test
    public void testInvalidateOtpForUser() {
        Otp otp = new Otp(1, "123456");
        when(otpRepository.findByUserId(1)).thenReturn(otp);

        otpService.invalidateOtpForUser(1);

        verify(otpRepository, times(1)).delete(otp);
    }
}
