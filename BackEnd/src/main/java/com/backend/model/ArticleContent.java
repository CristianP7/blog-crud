package com.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ArticleContent {

    @Id
    private int articleContentId;
    private int articleId;
    private String contentArticle;
    private String descriptionArticle;

    // Relaciones
    @OneToOne(mappedBy = "content")
    private Articles article;
}
