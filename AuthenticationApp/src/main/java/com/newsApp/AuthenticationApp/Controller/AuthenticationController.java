package com.newsApp.AuthenticationApp.Controller;

import com.newsApp.AuthenticationApp.Config.JwtTokenGen;
import com.newsApp.AuthenticationApp.Exception.UserNotFoundException;
import com.newsApp.AuthenticationApp.Model.AuthUser;
import com.newsApp.AuthenticationApp.Service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthUser authUser){
        try{
            if (authUser.getUserId() == null || authUser.getPassword() == null) {
                logger.error("email and password are null");
                throw new UserNotFoundException("email and password are null");
            }
            boolean result=authService.getAuthenticUser(authUser.getUserId(), authUser.getPassword());
            if(result)
            {
                Map<String, String> token= new JwtTokenGen().generateToken(authUser);
                logger.info("token generated");
                return new ResponseEntity<Map>(token,HttpStatus.OK);
            }else if (result == false) {
                logger.error("invalid email/password");
                throw new UserNotFoundException("Email / password mismatch");
            }
        }
        catch (UserNotFoundException e){
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity("Invalid user",HttpStatus.UNAUTHORIZED);
        }
        return null;
    }
}
