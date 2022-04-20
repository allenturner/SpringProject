package com.turner.tm.challenge.service;

import com.turner.tm.challenge.dto.ArtistDto;
import com.turner.tm.challenge.entity.ArtistEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    ArtistDto artistEntityToArtistDto(ArtistEntity artistEntity);
}
