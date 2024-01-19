package com.newsApp.UserDetails.Service;

import com.google.gson.Gson;
import com.newsApp.UserDetails.Exception.UserAlreadyExistException;
import com.newsApp.UserDetails.Exception.UserNotFoundException;
import com.newsApp.UserDetails.Model.User;
import com.newsApp.UserDetails.Rrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    //get user by user Id from db
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private Gson gson;
    private static final String TOPIC = "topic";
    @Override
    public User getUserById(Long userId) {
        Optional<User> userDetails = userRepository.findById(userId);
        if (userDetails.isPresent()){
            return userDetails.get();
        }
        return null;
    }
    //add the user details in db
    @Override
    public User addUser(User user) throws UserAlreadyExistException {
        Optional<User> existingProduct = userRepository.findById(user.getUserid());
        if (existingProduct.isPresent()) {
            throw new UserAlreadyExistException("User already exist");
        }
        else {
            userRepository.save(user);
            kafkaTemplate.send(TOPIC, gson.toJson(user));
            return user;
        }
    }
    //update existing user in db
    @Override
    public User updateUser(User updatedUser, Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User userUpdate = user.get();
            userUpdate.setName(updatedUser.getName());
            userUpdate.setPassword(updatedUser.getPassword());
            userUpdate.setEmail(updatedUser.getEmail());
            userUpdate.setCountry(updatedUser.getCountry());
            return userRepository.save(userUpdate);
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }
}