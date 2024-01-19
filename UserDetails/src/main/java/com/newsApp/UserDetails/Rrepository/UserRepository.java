package com.newsApp.UserDetails.Rrepository;

import com.newsApp.UserDetails.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
