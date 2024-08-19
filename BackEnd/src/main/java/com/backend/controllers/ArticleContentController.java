package com.backend.controllers;

import com.backend.model.ArticleContent;
import com.backend.model.dto.ArticleContentDTO;
import com.backend.service.IArticleContent;
import com.backend.service.impl.ArticleContentImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog/articleContents")
@PreAuthorize("permitAll()")
public class ArticleContentController {

    private final IArticleContent articleContentService;
    private final ModelMapper mapper;

    @GetMapping("/searchArticleContents")
    public ResponseEntity<Page<ArticleContentDTO>> searchArticleContents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "40") int size) throws Exception {

        Pageable pageable = PageRequest.of(page, size);
        Page<ArticleContent> articleContentPage = articleContentService.readAll(pageable);
        Page<ArticleContentDTO> articleDto = articleContentPage.map(this::converDto);
        return new ResponseEntity<>(articleDto, HttpStatus.OK);
    }

    private ArticleContentDTO converDto(ArticleContent article){
        return mapper.map(article, ArticleContentDTO.class);
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
