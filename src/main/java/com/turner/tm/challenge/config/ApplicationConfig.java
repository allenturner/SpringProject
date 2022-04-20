package com.turner.tm.challenge.config;

import com.turner.tm.challenge.entity.ArtistEntity;
import com.turner.tm.challenge.entity.EventEntity;
import com.turner.tm.challenge.entity.VenueEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationConfig {

    @Bean
    public Map<Long, ArtistEntity> artists() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long,VenueEntity> venues() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long,EventEntity> events() {
        return new HashMap<>();
    }
}
