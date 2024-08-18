package com.backend.model.dto;


import com.backend.model.UsersArticlesCreated;
import com.backend.model.UsersArticlesEdited;
import com.backend.model.UsersArticlesReaded;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticlesDTO {

    private Long id;
    private String title;
    private String description;
    private String image;

    private UsersDTO creator;
    private UsersDTO editor;
    @JsonIgnore
    private TopicDTO topic;

    @JsonManagedReference // Parte 'padre' de la relación
    private ArticleContentDTO articleContent;

    @JsonBackReference
    @JsonIgnore
    private List<UsersArticlesCreatedDTO> usersCreated;

    @JsonBackReference
    @JsonIgnore
    private List<UsersArticlesEditedDTO> usersEdited;

    @JsonBackReference
    @JsonIgnore
    private List<UsersArticlesReadedDTO> usersReaded;
}
