package com.turner.tm.challenge.entity;

import com.turner.tm.challenge.dto.VenueDto;
import lombok.Data;

import java.util.ArrayList;

@Data
public class EventEntity {
    private String title;
    private long id;
    private String dateStatus;
    private String timeZone;
    private String startDate;
    private ArrayList<ArtistEntity> artists;
    private VenueDto venue;
    private boolean hiddenFromSearch;
}
