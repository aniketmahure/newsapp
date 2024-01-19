package com.newsApp.FavoriteApp.service;

import com.newsApp.FavoriteApp.exception.ArticleExistAlreadyException;
import com.newsApp.FavoriteApp.exception.ArticleNotFoundException;
import com.newsApp.FavoriteApp.model.FavoriteArticle;

import java.util.List;

public interface FavoriteService {
    FavoriteArticle addFavoriteArticle(FavoriteArticle favoriteArticle) throws ArticleExistAlreadyException;
    List<FavoriteArticle> getAllFavoriteArticle(Long userid);
    String deleteArticle(Long userid,String title) throws ArticleNotFoundException;
}