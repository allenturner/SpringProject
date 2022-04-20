package com.turner.tm.challenge.config;

import com.turner.tm.challenge.entity.ArtistEntity;
import com.turner.tm.challenge.entity.EventEntity;
import com.turner.tm.challenge.entity.VenueEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class LoadDataFromUrl {
    private final WebClient client;
    @Autowired
    Map<Long,ArtistEntity> artists;

    @Autowired
    Map<Long,EventEntity> events;

    @Autowired
    Map<Long,VenueEntity> venues;

    @Value( "${venues.url}" )
    private String venuesUrl;
    @Value( "${events.url}" )
    private String eventsUrl;
    @Value( "${artists.url}" )
    private String artistsUrl;

    public LoadDataFromUrl(WebClient.Builder builder) {
        this.client = builder.build();
    }

    @PostConstruct
    public void init() {
        loadArtists();
        loadVenues();
        loadEvents();
    }

    private void loadVenues() {
        Mono<List<VenueEntity>> response = this.client.get().uri(venuesUrl).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });

        List<VenueEntity> venueEntityList = response.block();

        for (VenueEntity o : venueEntityList) {
            log.info(o.toString());
            venues.put(o.getId(), o);
        }
    }

    private void loadEvents() {
        Mono<List<EventEntity>> response = this.client.get().uri(eventsUrl).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<EventEntity>>() {
                });

        List<EventEntity> eventEntityList = response.block();

        for (EventEntity o : eventEntityList) {
            log.info(o.toString());
            events.put(o.getId(), o);
        }
    }

    private void loadArtists() {
        Mono<List<ArtistEntity>> response = this.client.get().uri(artistsUrl).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ArtistEntity>>() {
                });

        List<ArtistEntity> artistList = response.block();

        for (ArtistEntity o : artistList) {
            log.info(o.toString());
            artists.put(o.getId(), o);
        }
    }
}
