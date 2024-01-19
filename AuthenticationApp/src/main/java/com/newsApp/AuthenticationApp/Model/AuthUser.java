package com.newsApp.AuthenticationApp.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AuthUser {
    @Id
    private Long userid;
    private String password;
    private String role;

    public AuthUser(Long userid, String password, String role) {
        this.userid = userid;
        this.password = password;
        this.role = role;
    }

    public AuthUser() {
    }
    public AuthUser(Long userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public Long getUserId() {
        return userid;
    }

    public void setUserId(Long userId) {
        this.userid = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
