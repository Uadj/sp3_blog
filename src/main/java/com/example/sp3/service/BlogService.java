package com.example.sp3.service;

import com.example.sp3.domain.Article;
import com.example.sp3.dto.AddArticleRequest;
import com.example.sp3.dto.UpdateArticleRequest;
import com.example.sp3.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final ArticleRepository articleRepository;

    public Article save(AddArticleRequest request, String userName){
        return articleRepository.save(request.toEntity(userName));
    }
    public List<Article> findAll(){
        return articleRepository.findAll();
    }
    public Article findById(long id){
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: + " + id));
    }
    public void delete(long id){
        Article article = articleRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        authorizeArticleAuthor(article);
        articleRepository.delete(article);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    private static void authorizeArticleAuthor(Article article){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!article.getAuthor().equals(username)){
            throw new IllegalArgumentException("Not authorized");
        }
    }
}
