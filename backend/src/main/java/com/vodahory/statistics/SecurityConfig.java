package com.vodahory.statistics;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The {@code SecurityConfig} class is a Spring configuration class responsible for configuring security-related beans,
 * such as the password encoder used for encoding and validating passwords.
 *
 * <p>
 * This class is annotated with {@link org.springframework.context.annotation.Configuration} to indicate that it
 * contains Spring bean definitions and configuration settings.
 * </p>
 *
 * <p>
 * The {@link org.springframework.context.annotation.Bean} annotation is used to define a bean of type
 * {@link org.springframework.security.crypto.password.PasswordEncoder}, which provides password encoding and
 * validation functionality. In this case, it returns an instance of {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder}.
 * </p>
 *
 * <p>
 * This password encoder bean can be injected into other components to securely manage and validate passwords.
 * </p>
 *
 * <p>
 * It's important to note that this class does not configure other aspects of security; it's primarily focused on
 * providing a password encoder bean.
 * </p>
 *
 * @author Ing. Ekaterina Lavrova
 * @version 1.0
 * @since 2023-09-05
 */
@Configuration
public class SecurityConfig {
    /**
     * Defines a Spring bean for a password encoder.
     *
     * @return A {@link org.springframework.security.crypto.password.PasswordEncoder} bean,
     *         specifically an instance of {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
