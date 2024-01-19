package com.newsApp.FavoriteApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsApp.FavoriteApp.model.FavoriteArticle;
import com.newsApp.FavoriteApp.repository.FavoriteRepository;
import com.newsApp.FavoriteApp.service.FavoriteService;
import com.newsApp.FavoriteApp.service.FavoriteServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FavoriteAppControllerTest {
    private MockMvc mockMvc;
    @Mock
    private FavoriteService favoriteService;
    @InjectMocks
    private FavoriteController favoriteController;
    private FavoriteArticle favoriteArticle;
    private Optional optional;
    private List<FavoriteArticle> list;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(favoriteController).build();
        favoriteArticle = new FavoriteArticle(11L,1L,"password","user@mail","xyz.com");
    }
    @AfterEach
    void tearDown() {
        favoriteArticle = null;
    }
    @Test
    void givenUserToSaveThenShouldReturnSavedUser() throws Exception {
        when(favoriteController.favoriteService.addFavoriteArticle(favoriteArticle)).thenReturn(favoriteArticle);
        mockMvc.perform(post("/favorite/addFavorite/"+favoriteArticle.getUserid())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(favoriteArticle)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    public static String asJsonString(final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }
}