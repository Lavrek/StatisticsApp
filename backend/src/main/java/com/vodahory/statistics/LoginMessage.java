package com.vodahory.statistics;

/**
 * The {@code LoginMessage} class represents a message related to a login operation, including a status indicator.
 *
 * <p>
 * This class encapsulates two important pieces of information:
 * - The login-related message, which provides information about the login operation (e.g., success, failure reasons).
 * - The login status, which indicates the outcome of the login operation (e.g., success or failure).
 * </p>
 *
 * <p>
 * Instances of this class are commonly used to convey login-related information to callers of login services.
 * </p>
 *
 * @author Ing. Ekaterina Lavrova
 * @version 1.0
 * @since 2023-09-05
 */
public class LoginMessage {
    String message;
    Boolean status;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public LoginMessage(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
