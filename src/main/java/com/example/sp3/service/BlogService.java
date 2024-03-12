package com.example.sp3.service;

import com.example.sp3.domain.Article;
import com.example.sp3.dto.AddArticleRequest;
import com.example.sp3.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final ArticleRepository articleRepository;

    public Article save(AddArticleRequest request){
        return articleRepository.save(request.toEntity());
    }
    public List<Article> findAll(){
        return articleRepository.findAll();
    }
    public Article findById(long id){
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: + " + id));
    }
    public void delete(long id){
        articleRepository.deleteById(id);
    }
}
