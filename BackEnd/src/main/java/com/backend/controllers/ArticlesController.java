package com.backend.controllers;

import com.backend.model.Articles;
import com.backend.model.dto.ArticlesDTO;
import com.backend.service.impl.ArticlesImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/Articless")
public class ArticlesController {

    private final ArticlesImpl articlesService;

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
    private ArticlesDTO convertDto(Articles articles){
        return modelMapper.map(articles, ArticlesDTO.class);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Articles> getArticlesById(@PathVariable int id) {
        try {
            Articles Articles = articlesService.readById(id);
            return new ResponseEntity<>(Articles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Articles> createArticles(@RequestBody Articles Articles) {
        try {
            Articles createdArticles = articlesService.save(Articles);
            log.info("createdArticles: {}", createdArticles);
            return new ResponseEntity<>(createdArticles, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Articles> updateArticles(@PathVariable int id, @RequestBody Articles Articles) {
        try {
            Articles updatedArticles = articlesService.update(Articles, id);
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
