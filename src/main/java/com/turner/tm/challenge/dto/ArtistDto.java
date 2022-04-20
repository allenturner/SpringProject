package com.turner.tm.challenge.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArtistDto {
    private String name;
    private Long id;
    private String imgSrc;
    private String url;
    private int rank;
    private List<Long> eventsList;
}
