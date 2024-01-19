package com.newsApp.AuthenticationApp.Respository;

import com.newsApp.AuthenticationApp.Model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthUser,Long> {
    Optional<AuthUser> findByUseridAndPassword(Long userid, String password);
}