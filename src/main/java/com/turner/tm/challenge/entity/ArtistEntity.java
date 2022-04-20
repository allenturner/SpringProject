package com.turner.tm.challenge.entity;

import lombok.Data;

@Data
public class ArtistEntity {
    private long id;
    private String name;
    private String imgSrc;
    private String url;
    private int rank;
}
