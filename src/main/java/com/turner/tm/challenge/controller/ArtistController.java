package com.turner.tm.challenge.controller;

import com.turner.tm.challenge.dto.ArtistDto;
import com.turner.tm.challenge.service.ArtistService;
import com.turner.tm.challenge.util.ArtistNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebInputException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Slf4j
@Validated
@RestController
public class ArtistController {
    private ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artists/{id}")
    ArtistDto getArtist(@PathVariable("id") @NotNull @Positive Long id) {
        return artistService.getArtistById(id);
    }

    @ControllerAdvice
    public class ExceptionHelper {
        @ExceptionHandler(value = { Exception.class })
        public ResponseEntity<Object> handleException(Exception ex) {
            log.error("Exception: ",ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        @ExceptionHandler(value = { ArtistNotFoundException.class })
        public ResponseEntity<Object> handleArtistNotFoundException(ArtistNotFoundException ex) {
            log.warn(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(value = { ServerWebInputException.class })
        public ResponseEntity<Object> handleServerWebInputExceptionException(ServerWebInputException ex) {
            log.error("Exception: ",ex);
            return new ResponseEntity<>("Invalid artist id, a number is expected",HttpStatus.BAD_REQUEST);
        }

    }
}


