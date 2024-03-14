package com.statistics;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.statistics.repository.SecurityRepository;
import com.statistics.service.SecurityServiceImpl;
import com.statistics.model.Security;
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
        statistics.LoginDto loginDto = new statistics.LoginDto();
        loginDto.setLogin("testUser");
        loginDto.setPassword("testPassword");
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
        statistics.LoginDto loginDto = new statistics.LoginDto();
        loginDto.setLogin("testUser");
        loginDto.setPassword("testPassword");
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
        statistics.LoginDto loginDto = new statistics.LoginDto();
        loginDto.setLogin("nonExistentUser");
        loginDto.setPassword("testPassword");

        when(securityRepository.findByLogin("nonExistentUser")).thenReturn(null);

        LoginMessage loginMessage = securityService.loginSecurity(loginDto);

        assertFalse(loginMessage.getStatus());
        assertEquals("Email doesn't exist", loginMessage.getMessage());
    }
}
