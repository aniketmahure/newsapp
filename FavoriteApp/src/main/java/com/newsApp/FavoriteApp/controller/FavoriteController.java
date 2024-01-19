package com.newsApp.FavoriteApp.controller;

import com.newsApp.FavoriteApp.exception.ArticleExistAlreadyException;
import com.newsApp.FavoriteApp.exception.ArticleNotFoundException;
import com.newsApp.FavoriteApp.model.FavoriteArticle;
import com.newsApp.FavoriteApp.service.FavoriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
@CrossOrigin("*")
public class FavoriteController {
    private static Logger logger = LoggerFactory.getLogger(FavoriteController.class);

    @Autowired
    FavoriteService favoriteService;
    @GetMapping("/getFavorites/{userid}")
    public ResponseEntity<?> getArticlesById(@PathVariable("userid") Long userid){
        logger.info("Article fetch by userid");
        return new ResponseEntity<>(favoriteService.getAllFavoriteArticle(userid), HttpStatus.OK);
    }
    @PostMapping("/addFavorite/{userid}")
    public ResponseEntity<?> addFavoriteArticle(@RequestBody FavoriteArticle favoriteArticle,@PathVariable("userid") Long userid){
        favoriteArticle.setUserid(userid);
        try{
            favoriteService.addFavoriteArticle(favoriteArticle);
            logger.info("Article Added");
            return new ResponseEntity<>(favoriteArticle,HttpStatus.OK);
        }
        catch (ArticleExistAlreadyException e){
            logger.error("Cannot Add article");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/deleteFavorite/{userid}")
    public ResponseEntity<?> deleteFavoriteArticle(@RequestBody FavoriteArticle favoriteArticle,@PathVariable("userid") Long userid){
        try{
            favoriteService.deleteArticle(favoriteArticle.getUserid(),favoriteArticle.getTitle());
            logger.info("Article Deleted");
            return new ResponseEntity<>("Article Deleted Successfully",HttpStatus.OK);
        }
        catch (ArticleNotFoundException e){
            logger.error("Cannot Delete Article");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
