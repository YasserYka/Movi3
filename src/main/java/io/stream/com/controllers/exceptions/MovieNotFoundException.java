package io.stream.com.controllers.exceptions;

public class MovieNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MovieNotFoundException(Long id) {
        super("Movie with ID " + id + " not found");
    }
    
}
