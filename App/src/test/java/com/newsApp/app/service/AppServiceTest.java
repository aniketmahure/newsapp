package com.newsApp.app.service;

import com.newsApp.app.exception.ArticleNotFoundException;
import com.newsApp.app.model.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
@ExtendWith(MockitoExtension.class)
public class AppServiceTest {
    @InjectMocks
    NewsServiceImpl newsService;
    @Test
    public void testGetArticles_Success() throws ArticleNotFoundException {
        // Given
        RestTemplate restTemplate = new RestTemplate();
        String city = "cn";

        // When
        Collection collectionArticles = newsService.getArticles(city);

        // Then
        Assertions.assertNotNull(collectionArticles);
    }
    @Test
    public void testGetArticles_Success_Us() throws ArticleNotFoundException {
        // Given
        RestTemplate restTemplate = new RestTemplate();
        String city = "us";

        // When
        Collection collectionArticles = newsService.getArticles(city);

        // Then
        Assertions.assertNotNull(collectionArticles);
    }
    @Test
    public void testGetArticles_Success_India() throws ArticleNotFoundException {
        // Given
        RestTemplate restTemplate = new RestTemplate();
        String city = "in";

        // When
        Collection collectionArticles = newsService.getArticles(city);

        // Then
        Assertions.assertNotNull(collectionArticles);
    }
    @Test
    public void testGetArticles_Error() {
        // Given
        RestTemplate restTemplate = new RestTemplate();
        //2-letter ISO 3166-1 code of the country is needed
        String city = "china";

        Assertions.assertThrows(ArticleNotFoundException.class,()->{
            Collection collectionArticles = newsService.getArticles(city);
        });
    }
}
