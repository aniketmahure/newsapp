package com.newsApp.app.service;

import com.newsApp.app.exception.ArticleNotFoundException;
import com.newsApp.app.model.Collection;
public interface NewsService {
    Collection getArticles(String country) throws ArticleNotFoundException;
}