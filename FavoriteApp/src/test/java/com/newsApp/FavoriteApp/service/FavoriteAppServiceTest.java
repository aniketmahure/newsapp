package com.newsApp.FavoriteApp.service;

import com.newsApp.FavoriteApp.exception.ArticleExistAlreadyException;
import com.newsApp.FavoriteApp.exception.ArticleNotFoundException;
import com.newsApp.FavoriteApp.model.FavoriteArticle;
import com.newsApp.FavoriteApp.repository.FavoriteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FavoriteAppServiceTest {
    @Mock
    private FavoriteRepository favoriteRepository;
    @InjectMocks
    private FavoriteServiceImpl favoriteService;
    private FavoriteArticle favoriteArticle;
    private Optional optional;
    private List<FavoriteArticle> list;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        favoriteArticle = new FavoriteArticle(11L,1L,"password","user@mail","xyz.com");
        optional = Optional.of(favoriteArticle);
    }
    @AfterEach
    void tearDown() {
        favoriteArticle = null;
    }
    @Test
    void getUserIdAndTitleThenShouldReturnArticle() {
        when(favoriteRepository.findByUserid(favoriteArticle.getUserid())).thenReturn(List.of(favoriteArticle));
        assertNotNull(favoriteService.getAllFavoriteArticle(favoriteArticle.getUserid()));
    }
    @Test
    void givenArticleToSaveThenShouldReturnSavedArticle() throws ArticleExistAlreadyException {
        when(favoriteRepository.findByUseridAndTitle(favoriteArticle.getUserid(),favoriteArticle.getTitle())).thenReturn(Optional.empty());
        assertEquals(favoriteArticle,favoriteService.addFavoriteArticle(favoriteArticle));
    }
    @Test
    void givenArticleToSaveThenShouldNotReturnSavedArticle(){
        when(favoriteRepository.findByUseridAndTitle(favoriteArticle.getUserid(),favoriteArticle.getTitle())).thenReturn(Optional.ofNullable(favoriteArticle));
//        when(favoriteRepository.save(favoriteArticle)).thenThrow(new RuntimeException("already exist"));
        Assertions.assertThrows(ArticleExistAlreadyException.class,() -> {
            favoriteService.addFavoriteArticle(favoriteArticle);
        });
    }
    @Test
    void givenArticleToDeleteThenShouldDeleteArticle() throws ArticleNotFoundException {
        when(favoriteRepository.findByUseridAndTitle(favoriteArticle.getUserid(),favoriteArticle.getTitle())).thenReturn(Optional.ofNullable(favoriteArticle));
        Assertions.assertEquals("Article Deleted",favoriteService.deleteArticle(favoriteArticle.getUserid(),favoriteArticle.getTitle()));
    }
    @Test
    void givenArticleToDeleteThenShouldNotDeleteArticle() throws ArticleNotFoundException{
        when(favoriteRepository.findByUseridAndTitle(favoriteArticle.getUserid(),favoriteArticle.getTitle())).thenReturn(Optional.empty());
        Assertions.assertThrows(ArticleNotFoundException.class,() ->{
            favoriteService.deleteArticle(favoriteArticle.getUserid(),favoriteArticle.getTitle());
        });
    }
}
