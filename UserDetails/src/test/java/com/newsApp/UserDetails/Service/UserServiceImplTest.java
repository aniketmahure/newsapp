package com.newsApp.UserDetails.Service;

import com.google.gson.Gson;
import com.newsApp.UserDetails.Exception.UserAlreadyExistException;
import com.newsApp.UserDetails.Exception.UserNotFoundException;
import com.newsApp.UserDetails.Model.User;
import com.newsApp.UserDetails.Rrepository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    //Mock UserRepository
    @Mock
    private UserRepository userRepository;
    // Inject Mocks to UserServiceImpl
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    private Optional optional;
    private Gson gson;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User(1L,"user","password","user@mail","india");
        optional = Optional.of(user);
    }

    @AfterEach
    void tearDown() {
        user = null;
    }
//    //test case for asserting User when addUser() method is called
//    @Test
//    void getUserIdThenShouldReturnUser() throws UserAlreadyExistException {
//        when(userRepository.save(any())).thenReturn(user);
//        when(userService.addUser(user)).thenReturn(user);
//        assertEquals(user,userService.addUser(user));
//        verify(userRepository,times(1)).save(any());
//    }
    //test case for asserting Exception when addUser() method is called
    @Test
    void givenUserToSaveThenShouldNotReturnSavedUser(){
        when(userRepository.save(user)).thenThrow(new RuntimeException("already exist"));
        Assertions.assertThrows(RuntimeException.class,() -> {
            userService.addUser(user);
        });
        verify(userRepository, times(1)).save(any());
    }
    //test case for asserting country after calling updateUser() method
    @Test
    void givenUserToUpdateThenShouldReturnUpdatedUser() throws UserNotFoundException {
        when(userRepository.findById(user.getUserid())).thenReturn(optional);
        when(userRepository.save(user)).thenReturn(user);
        user.setCountry("Us");
        User blog1 = userService.updateUser(user,user.getUserid());
        assertEquals(blog1.getCountry(), "Us");
        verify(userRepository, times(1)).save(user);
        verify(userRepository, atMost(2)).findById(user.getUserid());
    }
    //test case for asserting Exception when updateUser() method is called
    @Test
    void givenUserToUpdateThenShouldNotReturnUpdatedUser(){
        //when(userRepository.save(user)).thenReturn(user);
        Assertions.assertThrows(UserNotFoundException.class,() -> {
            userService.updateUser(user,user.getUserid());
        });
        verify(userRepository, times(1)).findById(any());
    }
}