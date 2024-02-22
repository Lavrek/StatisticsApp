package com.vodahory.statistics.service;


import com.vodahory.statistics.LoginMessage;
import com.vodahory.statistics.dtos.LoginDto;
import com.vodahory.statistics.model.Security;
import com.vodahory.statistics.repository.SecurityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    SecurityRepository securityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginMessage loginSecurity(LoginDto loginDto) {
        Security security = securityRepository.findByLogin(loginDto.getLogin());

        if (security != null) {
            String enteredPassword = loginDto.getPassword();
            String encodedPasswordInDatabase = security.getPassword();

            boolean isPasswordMatch = passwordEncoder.matches(enteredPassword, encodedPasswordInDatabase);

            if (isPasswordMatch) {
                return new LoginMessage("Login Success", true);
            } else {
                return new LoginMessage("Wrong password", false);
            }
        } else {
            return new LoginMessage("Email doesn't exist", false);
        }
    }
}
