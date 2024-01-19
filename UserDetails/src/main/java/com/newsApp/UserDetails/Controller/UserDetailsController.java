package com.newsApp.UserDetails.Controller;

import com.newsApp.UserDetails.Exception.UserAlreadyExistException;
import com.newsApp.UserDetails.Exception.UserNotFoundException;
import com.newsApp.UserDetails.Model.User;
import com.newsApp.UserDetails.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserDetailsController {
    private static Logger logger = LoggerFactory.getLogger(UserDetailsController.class);
    @Autowired
    UserService userService;
    //get user by id
    @GetMapping("/viewUserById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }
    //add user
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try{
            User addedUser = userService.addUser(user);
            logger.info("User Added");
            return new ResponseEntity<>(addedUser, HttpStatus.OK);
        }catch (UserAlreadyExistException e){
            logger.error("Cannot Add User, User Already Exist");
            return new ResponseEntity<>("user already exist",HttpStatus.CONFLICT);
        }
    }
    //update existing user
    @PostMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("userId") Long userId) {
        try{
            User updatedUser = userService.updateUser(user,userId);
            logger.info("User Updated");
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            logger.error("Cannot Update User, User Not Found");
            return new ResponseEntity<>("user not found",HttpStatus.CONFLICT);
        }
    }
}