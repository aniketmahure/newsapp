package com.newsApp.app.model;

import java.util.List;

public class Collection {
    private String status;
    private String totalResults;
    private List<NewsArticle> articles;

    public Collection(List<NewsArticle> articles) {
        this.articles = articles;
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticle> articles) {
        this.articles = articles;
    }

    public Collection() {
    }

    public Collection(String status, String totalResults, List<NewsArticle> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }
}
