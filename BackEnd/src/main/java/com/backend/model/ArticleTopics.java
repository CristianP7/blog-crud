package com.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ArticleTopics {

    @Id
    private Integer articleTopicsId;

    private int topicId;
    private int articleId;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "topicId", insertable = false, updatable = false)
    private Topics topic;  // Un artículo está relacionado con un tema

    @ManyToOne
    @JoinColumn(name = "articleId", insertable = false, updatable = false)
    private Articles article;
}
