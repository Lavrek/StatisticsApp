package com.vodahory.statistics;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.vodahory.statistics.dtos.LoginDto;
import com.vodahory.statistics.model.Security;
import com.vodahory.statistics.repository.SecurityRepository;
import com.vodahory.statistics.service.SecurityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityServiceImplTest {

    private SecurityServiceImpl securityService;
    private SecurityRepository securityRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        securityRepository = mock(SecurityRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        securityService = new SecurityServiceImpl(securityRepository, passwordEncoder);
    }

    @Test
    void testLoginSecuritySuccess() {
        LoginDto loginDto = new LoginDto("testUser", "testPassword");
        Security mockSecurity = new Security();
        mockSecurity.setLogin("testUser");
        mockSecurity.setPassword("hashedPassword");

        when(securityRepository.findByLogin("testUser")).thenReturn(mockSecurity);
        when(passwordEncoder.matches("testPassword", "hashedPassword")).thenReturn(true);

        LoginMessage loginMessage = securityService.loginSecurity(loginDto);

        assertTrue(loginMessage.getStatus());
        assertEquals("Login Success", loginMessage.getMessage());
    }

    @Test
    void testLoginSecurityWrongPassword() {
        LoginDto loginDto = new LoginDto("testUser", "testPassword");
        Security mockSecurity = new Security();
        mockSecurity.setLogin("testUser");
        mockSecurity.setPassword("hashedPassword");

        when(securityRepository.findByLogin("testUser")).thenReturn(mockSecurity);
        when(passwordEncoder.matches("testPassword", "hashedPassword")).thenReturn(false);

        LoginMessage loginMessage = securityService.loginSecurity(loginDto);

        assertFalse(loginMessage.getStatus());
        assertEquals("Wrong password", loginMessage.getMessage());
    }

    @Test
    void testLoginSecurityEmailNotExists() {
        LoginDto loginDto = new LoginDto("nonExistentUser", "testPassword");

        when(securityRepository.findByLogin("nonExistentUser")).thenReturn(null);

        LoginMessage loginMessage = securityService.loginSecurity(loginDto);

        assertFalse(loginMessage.getStatus());
        assertEquals("Email doesn't exist", loginMessage.getMessage());
    }
}
