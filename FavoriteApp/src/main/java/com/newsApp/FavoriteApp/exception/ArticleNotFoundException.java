package com.newsApp.FavoriteApp.exception;

public class ArticleNotFoundException extends Exception {
    public ArticleNotFoundException(String message){
        super(message);
    }
}
