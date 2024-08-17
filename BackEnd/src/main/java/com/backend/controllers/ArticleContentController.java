package com.backend.controllers;

import com.backend.model.ArticleContent;
import com.backend.service.impl.ArticleContentImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articleContents")
public class ArticleContentController {

    private final ArticleContentImpl articleContentService;

    @GetMapping("/searchArticleContents")
    public ResponseEntity<Page<ArticleContent>> searchArticleContents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "40") int size) throws Exception {

        Pageable pageable = PageRequest.of(page, size);
        Page<ArticleContent> articleContentPage = articleContentService.readAll(pageable);
        return new ResponseEntity<>(articleContentPage, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ArticleContent> getArticleContentById(@PathVariable int id) {
        try {
            ArticleContent articleContent = articleContentService.readById(id);
            return new ResponseEntity<>(articleContent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ArticleContent> createArticleContent(@RequestBody ArticleContent articleContent) {
        try {
            ArticleContent createdArticleContent = articleContentService.save(articleContent);
            return new ResponseEntity<>(createdArticleContent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleContent> updateArticleContent(@PathVariable int id, @RequestBody ArticleContent articleContent) {
        try {
            ArticleContent updatedArticleContent = articleContentService.update(articleContent, id);
            return new ResponseEntity<>(updatedArticleContent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteArticleContent(@PathVariable int id) {
        try {
            articleContentService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
