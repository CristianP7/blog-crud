package com.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "article_creator")
    private Users creator;

    @ManyToOne
    @JoinColumn(name = "article_editor")
    private Users editor;

    @ManyToOne
    @JoinColumn(name = "article_content_id")
    private ArticleContent articleContent;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topics topic;

    @OneToMany(mappedBy = "article")
    private List<UsersArticlesCreated> usersCreated;

    @OneToMany(mappedBy = "article")
    private List<UsersArticlesEdited> usersEdited;

    @OneToMany(mappedBy = "article")
    private List<UsersArticlesReaded> usersReaded;
}
