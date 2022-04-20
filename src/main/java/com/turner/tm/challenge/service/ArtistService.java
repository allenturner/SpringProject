package com.turner.tm.challenge.service;

import com.turner.tm.challenge.dto.ArtistDto;
import com.turner.tm.challenge.dto.EventDto;
import com.turner.tm.challenge.entity.ArtistEntity;
import com.turner.tm.challenge.entity.EventEntity;
import com.turner.tm.challenge.entity.VenueEntity;
import com.turner.tm.challenge.util.ArtistNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ArtistService {
    private ArtistMapper artistMapper;
    private Map<Long, ArtistEntity> artists;
    private Map<Long, EventEntity> events;
    private Map<Long, VenueEntity> venues;

    public ArtistService(ArtistMapper inArtistMapper, Map<Long, ArtistEntity> inArtists, Map<Long, EventEntity> inEvents) {
        this.artistMapper = inArtistMapper;
        this.artists = inArtists;
        this.events = inEvents;
    }

    public ArtistDto getArtistById(Long id) {
        ArtistEntity artistEntity = artists.get(id);
        if (artistEntity == null) {
            throw new ArtistNotFoundException(id);
        } else {
            List<Long> eventsList = new ArrayList<>();

            for (Long eventId : events.keySet())
            {
                EventEntity eventEntity = events.get(eventId);
                ArrayList<ArtistEntity> artists = eventEntity.getArtists();
                for (ArtistEntity artist : artists) {
                    if(artist.getId() == id){
                        eventsList.add(eventId);
                    }
                }
            }
            ArtistDto artistDto = artistMapper.artistEntityToArtistDto(artistEntity);
            artistDto.setEventsList(eventsList);
            return artistDto;
        }
    }
}
