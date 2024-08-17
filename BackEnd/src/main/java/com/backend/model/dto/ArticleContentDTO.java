package com.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleContentDTO {
    private int articleContentId;
    private int articleId;
    private String contentArticle;
    private String descriptionArticle;
    @JsonBackReference
    private ArticlesDTO article;
}