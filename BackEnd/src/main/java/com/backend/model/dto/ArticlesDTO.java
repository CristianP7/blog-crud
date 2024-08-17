package com.backend.model.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticlesDTO {
    private int articleId;
    private String imageArticle;
    private String titleArticle;
    private int articleContentId;
    private int topicId;

    @JsonBackReference
    private List<UsersArticReadedDTO> usersArticReaded;

    @JsonBackReference
    private List<UsersArticCreatedDTO> usersArticCreated;

    @JsonBackReference
    private List<UsersArticEditedDTO> usersArticEdited;

    @JsonManagedReference
    private ArticleContentDTO articleContent;

    @JsonManagedReference
    private ArticleTopicsDTO topic;
}
