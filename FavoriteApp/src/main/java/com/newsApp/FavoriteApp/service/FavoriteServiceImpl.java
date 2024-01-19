package com.newsApp.FavoriteApp.service;

import com.newsApp.FavoriteApp.exception.ArticleExistAlreadyException;
import com.newsApp.FavoriteApp.exception.ArticleNotFoundException;
import com.newsApp.FavoriteApp.model.FavoriteArticle;
import com.newsApp.FavoriteApp.repository.FavoriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService{
    @Autowired
    FavoriteRepository favoriteRepository;
    @Override
    public FavoriteArticle addFavoriteArticle(FavoriteArticle favoriteArticle) throws ArticleExistAlreadyException {
        Optional<FavoriteArticle> existingProduct = favoriteRepository
                .findByUseridAndTitle(favoriteArticle.getUserid(),favoriteArticle.getTitle());
        if (existingProduct.isPresent()) {
            throw new ArticleExistAlreadyException("Article already exist");
        }
        else {
            favoriteRepository.save(favoriteArticle);
            return favoriteArticle;
        }
    }
    @Override
    public List<FavoriteArticle> getAllFavoriteArticle(Long userid){
        List<FavoriteArticle> allArticles = favoriteRepository.findByUserid(userid);
        return allArticles;
    }
    @Override
    public String deleteArticle(Long userid, String title) throws ArticleNotFoundException {
        Optional<FavoriteArticle> existingProduct = favoriteRepository.findByUseridAndTitle(userid,title);
        if (existingProduct.isPresent()){
            favoriteRepository.deleteById(existingProduct.get().getFavouriteId());
            //favoriteRepository.deleteByUseridAndTitle(userid,title);
            return "Article Deleted";
        }else
            throw new ArticleNotFoundException("Article Not Found");
    }
}
