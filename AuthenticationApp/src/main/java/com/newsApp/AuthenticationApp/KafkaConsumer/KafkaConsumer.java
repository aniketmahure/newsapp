package com.newsApp.AuthenticationApp.KafkaConsumer;

import com.google.gson.Gson;
import com.newsApp.AuthenticationApp.Exception.UserAlreadyExistException;
import com.newsApp.AuthenticationApp.Model.AuthUser;
import com.newsApp.AuthenticationApp.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
@Configuration
public class KafkaConsumer {
    @Autowired
    Gson gson;
    @Autowired
    AuthService authService;

    @KafkaListener(topics = "topic",groupId = "group_id")
    public void consume(String user) throws UserAlreadyExistException {
        System.out.println("received message = " + user);
        AuthUser userdata=gson.fromJson(user, AuthUser.class);
        AuthUser result = authService.addAuthenticUser(userdata);
        System.out.println("stored data in user copy" + userdata.toString());
    }
}