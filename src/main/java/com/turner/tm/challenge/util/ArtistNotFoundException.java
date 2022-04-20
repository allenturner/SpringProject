package com.turner.tm.challenge.util;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(Long id) {
        super("Artist Not Found, id: " + id);
    }
}
