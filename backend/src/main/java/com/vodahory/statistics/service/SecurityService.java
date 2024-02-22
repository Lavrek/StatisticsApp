package com.vodahory.statistics.service;

import com.vodahory.statistics.LoginMessage;
import com.vodahory.statistics.dtos.LoginDto;

/**
 * Service interface for managing security-related operations.
 */
public interface SecurityService {

    /**
     * Attempts to authenticate a user based on the provided login credentials.
     *
     * @param loginDto The data transfer object containing user login information.
     * @return A {@link LoginMessage} indicating the result of the login attempt.
     */
    public LoginMessage loginSecurity(LoginDto loginDto);
}
