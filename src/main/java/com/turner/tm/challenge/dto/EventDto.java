package com.turner.tm.challenge.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class EventDto {
    private String title;
    private Long id;
    private String dateStatus;
    private ArrayList<ArtistDto> artistDtos;
    private VenueDto venue;
    private boolean hiddenFromSearch;
}
