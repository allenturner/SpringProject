package com.turner.tm.challenge.service;

import com.turner.tm.challenge.dto.ArtistDto;
import com.turner.tm.challenge.entity.ArtistEntity;
import com.turner.tm.challenge.entity.EventEntity;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class ArtistServiceTest {

    @Test
    public void getArtistById(){
        ArtistMapper artistMapper = new ArtistMapperImpl();

        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("The Crazy World of Arthur Brown");
        artistEntity.setId(27L);
        artistEntity.setImgSrc("//some-base-url/arthur-brown.jpg");
        artistEntity.setUrl("/the-crazy-world-of-arthur-brown-tickets/artist/27");

        Map<Long, ArtistEntity> artists = new HashMap<>();
        artists.put(27L, artistEntity);

        Map<Long, EventEntity> events = new HashMap<>();
        EventEntity eventEntity = new EventEntity();
        eventEntity.setTitle("Blues In Space");
        eventEntity.setId(2L);
        eventEntity.setDateStatus("singleDate");
        eventEntity.setTimeZone("Europe/London");
        eventEntity.setStartDate("2020-10-18T00:00:00");
        ArrayList<ArtistEntity> artistList = new ArrayList<>();
        artistList.add(artistEntity);
        eventEntity.setArtists(artistList);
        events.put(2L, eventEntity);

        ArtistService anArtistService = new ArtistService(artistMapper, artists, events);
        ArtistDto artistById = anArtistService.getArtistById(27L);

        assertEquals(artistEntity.getName(), artistById.getName());
        assertEquals(artistEntity.getId(), artistById.getId());
        assertEquals(artistEntity.getImgSrc(), artistById.getImgSrc());
        assertEquals(artistEntity.getUrl(), artistById.getUrl());
        assertEquals(eventEntity.getId(), artistById.getEventsList().get(0));
    }

}