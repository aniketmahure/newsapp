package com.newsApp.app.controller;

import com.newsApp.app.exception.ArticleNotFoundException;
import com.newsApp.app.model.Collection;
import com.newsApp.app.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@CrossOrigin("*")
public class NewsController {
    private static Logger logger = LoggerFactory.getLogger(NewsController.class);
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/viewAll/{country}")
    public ResponseEntity<?> getAllArticles(@PathVariable("country") String country){
        try{
            Collection articlesFromApi =newsService.getArticles(country);
            if (articlesFromApi.getArticles().isEmpty()){
                throw new ArticleNotFoundException("Article Not Found");
            }
            logger.info("List of Article Found");
            return new ResponseEntity<>(articlesFromApi.getArticles(), HttpStatus.OK);
        }catch (ArticleNotFoundException e){
            logger.info("Exception thrown");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}