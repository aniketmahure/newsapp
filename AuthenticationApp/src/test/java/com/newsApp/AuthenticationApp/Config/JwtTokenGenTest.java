package com.newsApp.AuthenticationApp.Config;

import com.newsApp.AuthenticationApp.Model.AuthUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JwtTokenGenTest {
    private AuthUser user;
    @Autowired
    private JwtTokenGen jwtTokenGen;
    private Map<String,String> tokenMap;

    @BeforeEach
    public void setup() {
        user = new AuthUser(1L, "andy@email.com");
        tokenMap = new HashMap<>();
    }
    @Test
    void givenAUserThenCallToGenerateTokenShouldReturnNotNull() {
        /*Act*/
        tokenMap = jwtTokenGen.generateToken(user);
        /*Assert*/
        assertNotNull(tokenMap);
    }

    @Test
    void givenAUserThenShouldReturnExpectedKeysInMap() {
        /*ACt*/
        tokenMap = jwtTokenGen.generateToken(user);
        /*Assert*/
        assertNotNull(tokenMap.containsKey("token"));
        assertNotNull(tokenMap.containsKey("message"));
    }

    @Test
    void givenAUserThenShouldReturnExpectedTokenInMap() {
        /*ACt*/
        tokenMap = jwtTokenGen.generateToken(user);
        /*Assert*/
        assertThat(tokenMap.get("token").length()).isGreaterThan(20);
    }

    @Test
    void givenAUserThenShouldReturnExpectedClaimInMap() {
        /*ACt*/
        tokenMap = jwtTokenGen.generateToken(user);
        /*Assert*/
        assertThat(tokenMap.get("message")).isEqualTo("Login Successful");
    }
}
