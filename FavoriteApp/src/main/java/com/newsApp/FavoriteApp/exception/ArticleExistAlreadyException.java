package com.newsApp.FavoriteApp.exception;

public class ArticleExistAlreadyException extends Exception{
    public ArticleExistAlreadyException(String message){
        super(message);
    }
}