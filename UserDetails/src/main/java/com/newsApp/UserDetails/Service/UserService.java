package com.newsApp.UserDetails.Service;

import com.newsApp.UserDetails.Exception.UserAlreadyExistException;
import com.newsApp.UserDetails.Exception.UserNotFoundException;
import com.newsApp.UserDetails.Model.User;

public interface UserService {
    User getUserById(Long userId);
    User addUser(User user) throws UserAlreadyExistException;
    User updateUser(User updatedUser, Long productId) throws UserNotFoundException;
}