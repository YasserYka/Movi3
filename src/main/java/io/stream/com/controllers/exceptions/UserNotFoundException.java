package io.stream.com.controllers.exceptions;

public class UserNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super("User with ID " + id + " not found");
    }
    
    public UserNotFoundException(String email){
        super("User with Email " + email + " not found");
    }
}
