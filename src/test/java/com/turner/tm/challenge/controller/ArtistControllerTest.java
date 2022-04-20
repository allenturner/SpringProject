package com.turner.tm.challenge.controller;

import com.turner.tm.challenge.dto.ArtistDto;
import com.turner.tm.challenge.service.ArtistService;
import com.turner.tm.challenge.util.ArtistNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.server.ServerWebInputException;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArtistController.class)
class ArtistControllerTest {
    @MockBean
    private ArtistService artistService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnArtist() throws Exception {
        long id = 27L;
        ArtistDto anArtistDto = new ArtistDto();
        anArtistDto.setName("The Crazy World of Arthur Brown");
        anArtistDto.setId(27L);
        anArtistDto.setImgSrc("//some-base-url/arthur-brown.jpg");
        anArtistDto.setUrl("/the-crazy-world-of-arthur-brown-tickets/artist/27");
        List<Long> eventsList = Arrays.asList(Long.valueOf(2), Long.valueOf(12));
        anArtistDto.setEventsList(eventsList);

        when(artistService.getArtistById(id)).thenReturn(anArtistDto);

        mockMvc.perform(get("/artists//{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(anArtistDto.getName()))
                .andExpect(jsonPath("$.imgSrc").value(anArtistDto.getImgSrc()))
                .andExpect(jsonPath("$.url").value(anArtistDto.getUrl()))
                .andExpect(jsonPath("$.rank").value(anArtistDto.getRank()))
                .andExpect(jsonPath("$.eventsList[0]").value(Long.valueOf(2)))
                .andExpect(jsonPath("$.eventsList[1]").value(Long.valueOf(12)))
                .andDo(print());
    }

    @Test
    void shouldReturnArtistNotFound() throws Exception {
        long id = 1L;
        ArtistNotFoundException ex = new ArtistNotFoundException(id);
        when(artistService.getArtistById(id)).thenThrow(ex);
        mockMvc.perform(get("/artists/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldReturnInvalidArtistId() throws Exception {
        long id = 1L;
        ServerWebInputException ex = new ServerWebInputException("");
        when(artistService.getArtistById(id)).thenThrow(ex);
        mockMvc.perform(get("/artists/{id}", id))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}