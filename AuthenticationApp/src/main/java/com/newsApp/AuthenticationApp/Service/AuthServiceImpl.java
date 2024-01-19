package com.newsApp.AuthenticationApp.Service;

import com.newsApp.AuthenticationApp.Exception.UserAlreadyExistException;
import com.newsApp.AuthenticationApp.Exception.UserNotFoundException;
import com.newsApp.AuthenticationApp.Model.AuthUser;
import com.newsApp.AuthenticationApp.Respository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    AuthRepository authRepository;
    @Override
    public AuthUser addAuthenticUser(AuthUser authUser) throws UserAlreadyExistException {
        Optional<AuthUser> existingUser = authRepository.findById(authUser.getUserId());
        if (existingUser.isPresent()){
            throw new UserAlreadyExistException("User Already Exist");
        }
        authRepository.save(authUser);
        return authUser;
    }
    @Override
    public boolean getAuthenticUser(Long userid, String password) throws UserNotFoundException {
        Optional<AuthUser> optUserInfo=	authRepository.findByUseridAndPassword(userid, password);
        if(optUserInfo.isPresent())
            return true;
        else
            throw new UserNotFoundException("User Not Found");
    }
}