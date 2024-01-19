package com.newsApp.UserDetails.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsApp.UserDetails.Exception.UserAlreadyExistException;
import com.newsApp.UserDetails.Exception.UserNotFoundException;
import com.newsApp.UserDetails.Model.User;
import com.newsApp.UserDetails.Service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserDetailsControllerTest {

    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserDetailsController userDetailsController;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userDetailsController).build();
        user = new User(1L,"user","password","user@mail","india");
    }

    @AfterEach
    void tearDown() {
        user = null;
    }
    @Test
    void givenUserToSaveThenShouldReturnSavedUser() throws Exception {
        when(userService.addUser(any())).thenReturn(any());
        mockMvc.perform(post("/user/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    //test case for asserting country after calling updateUser() method
    @Test
    void givenUserToUpdateThenShouldReturnUpdatedUser() throws Exception {
        when(userService.addUser(user)).thenReturn(user);
        userService.addUser(user);
        mockMvc.perform(post("/user/updateUser/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    public static String asJsonString(final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }
}