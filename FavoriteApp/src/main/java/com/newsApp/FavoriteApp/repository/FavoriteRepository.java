package com.newsApp.FavoriteApp.repository;

import com.newsApp.FavoriteApp.model.FavoriteArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteArticle,Long> {
    List<FavoriteArticle> findByUserid(Long userid);
    Optional deleteByUseridAndTitle(Long userid, String title);
    Optional<FavoriteArticle> findByUseridAndTitle(Long userid, String title);
}
