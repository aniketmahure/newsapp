package com.newsApp.AuthenticationApp.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsApp.AuthenticationApp.Exception.UserNotFoundException;
import com.newsApp.AuthenticationApp.Model.AuthUser;
import com.newsApp.AuthenticationApp.Service.AuthService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    private MockMvc mockMvc;
    @Mock
    private AuthService authService;
    @InjectMocks
    private AuthenticationController authController;
    private AuthUser user;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        user  = new AuthUser(1L, "123456");
    }
    @AfterEach
    void tearDown() {
        user = null;
    }
    @Test
    void givenUserToLoginThenShouldReturnToken() throws Exception {
        when(authService.getAuthenticUser(user.getUserId(),user.getPassword())).thenReturn(true);
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void givenInvalidUserToLoginThenShouldNotReturnToken() throws Exception {
        //authService.addAuthenticUser(user);
        user.setPassword("123");
        when(authService.getAuthenticUser(user.getUserId(),user.getPassword())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());
    }
    public static String asJsonString(final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
