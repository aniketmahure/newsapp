package com.newsApp.app.exception;

public class ArticleNotFoundException extends Exception {
    public ArticleNotFoundException(String message){
        super(message);
    }
}