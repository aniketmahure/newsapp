package com.newsApp.AuthenticationApp.Service;

import com.newsApp.AuthenticationApp.Exception.UserAlreadyExistException;
import com.newsApp.AuthenticationApp.Exception.UserNotFoundException;
import com.newsApp.AuthenticationApp.Model.AuthUser;

public interface AuthService {
    AuthUser addAuthenticUser(AuthUser authUser) throws UserAlreadyExistException;
    boolean getAuthenticUser(Long userid,String password) throws UserNotFoundException;
}