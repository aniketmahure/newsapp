package com.newsApp.AuthenticationApp.Config;

import com.newsApp.AuthenticationApp.Model.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenGen {
    public Map<String, String> generateToken(AuthUser user) {
        String jwtToken = "";
        /*
         * Generate JWT token and store in String jwtToken
         */
        Map<String, String> jwtTokenMap = new HashMap<>();
        Map<String, Object> userdata = new HashMap<>();
        userdata.put("id", user.getUserId().toString());
        userdata.put("password", user.getPassword());
        jwtToken = Jwts.builder().setClaims(userdata)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secret").compact();
        jwtTokenMap.put("token", jwtToken);
        jwtTokenMap.put("message", "Login Successful");
        System.out.println("Token Generated");
        return jwtTokenMap;
    }
}
