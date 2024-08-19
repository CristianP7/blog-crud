package com.backend.controllers;

import com.backend.model.Articles;
import com.backend.model.dto.ArticlesDTO;
import com.backend.service.IArticle;
import com.backend.service.IArticleContent;
import com.backend.service.impl.ArticleContentImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/blog/articles")
@PreAuthorize("permitAll()")
public class ArticlesController {

    private final IArticle articlesService;
    private final IArticleContent articlesContent;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ArticlesDTO>> getAllArticles() {
        try {
            List<Articles> articles = articlesService.readAll();
            List<ArticlesDTO> articleDTOs = articles.stream()
                    .map(this::convertDto)
                    .toList();
            return new ResponseEntity<>(articleDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/topic/{name}")
    public ResponseEntity<List<ArticlesDTO>> getArticlesByTopicName(@PathVariable String name) {
        List<Articles> articlesList = articlesService.findByTopicName(name);
        List<ArticlesDTO> articlesDtoList = articlesList.stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(articlesDtoList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArticlesDTO>> getArticlesBySearchTerm(@RequestParam String searchTerm) {
        List<Articles> articlesList = articlesService.findByTitleContainsOrArticleContent_ContentContains(searchTerm, searchTerm);
        List<ArticlesDTO> articlesDtoList = articlesList.stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(articlesDtoList, HttpStatus.OK);
    }

    private ArticlesDTO convertDto(Articles articles){
        return modelMapper.map(articles, ArticlesDTO.class);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ArticlesDTO> getArticlesById(@PathVariable int id) {
        try {
            Articles Articles = articlesService.readById(id);
            ArticlesDTO articlesDTO = convertDto(Articles);
            return new ResponseEntity<>(articlesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Articles> createArticles(@RequestBody Articles articles) {
        try {
            articlesContent.save(articles.getArticleContent());
            Articles createdArticles = articlesService.save(articles);
            return new ResponseEntity<>(createdArticles, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Articles> updateArticles(@PathVariable int id, @RequestBody Articles Articles) {
        try {
            Articles updatedArticles = articlesService.update(Articles, id);
            log.info("Updated Articles: {}", updatedArticles);
            return new ResponseEntity<>(updatedArticles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteArticles(@PathVariable int id) {
        try {
            articlesService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
