package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Articles {

    @Id
    private int articleId;
    private String imageArticle;
    private String titleArticle;
    private int articleContentId;
    private int topicId;

    // Relaciones
    @OneToOne
    @JoinColumn(name = "articleContentId", insertable = false, updatable = false)
    private ArticleContent content;  // Un artículo tiene un contenido

    @ManyToOne
    @JoinColumn(name = "topicId", insertable = false, updatable = false)
    private Topics topic;  // Un artículo pertenece a un tema

    @OneToMany(mappedBy = "article")
    private List<UsersArticReaded> usersReaded;  // Un artículo puede ser leído por varios usuarios

    @OneToMany(mappedBy = "article")
    private List<UsersArticCreated> usersCreated;  // Un artículo puede ser creado por varios usuarios

    @OneToMany(mappedBy = "article")
    private List<UsersArticEdited> usersEdited;  // Un artículo puede ser editado por varios usuarios

    @OneToMany(mappedBy = "article")
    private List<ArticleTopics> articleTopics;
}
