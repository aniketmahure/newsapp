package com.newsApp.app.service;

import com.newsApp.app.exception.ArticleNotFoundException;
import com.newsApp.app.model.Collection;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsServiceImpl implements NewsService {
    //api key here
    private static final String API_KEY = "9819a68d6a7f4131a0e655ad13d617f3";
    //the base api url here
    private static final String API_URL = "https://newsapi.org/v2/top-headlines";
    public final RestTemplate restTemplate = new RestTemplate();

    //using rest template, get the news article details of a country
    //2-letter ISO 3166-1 code of the country is needed
    public Collection getArticles(String country) throws ArticleNotFoundException {
        Collection newsCollection = restTemplate.getForObject(API_URL+"?country="+country+"&apiKey="+API_KEY,Collection.class);
        System.out.println(newsCollection.getArticles());
        if (newsCollection.getArticles().isEmpty()){
            throw new ArticleNotFoundException("Articles Not Found");
        }
        return (Collection) newsCollection;
    }
}