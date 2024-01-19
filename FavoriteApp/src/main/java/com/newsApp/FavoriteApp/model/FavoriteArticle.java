package com.newsApp.FavoriteApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class FavoriteArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long favouriteId;
    private Long userid;
    private String author;
    private String title;
    private String url;
    public FavoriteArticle() {
    }
    public FavoriteArticle(Long favouriteId, Long userid, String author, String title, String url) {
        this.favouriteId = favouriteId;
        this.userid = userid;
        this.author = author;
        this.title = title;
        this.url = url;
    }

    public Long getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(Long favouriteId) {
        this.favouriteId = favouriteId;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
