package com.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTopicsDTO {
    private int articleTopicsId;
    private int topicId;
    private int articleId;
    private TopicDTO topic;

    @JsonBackReference
    private ArticlesDTO article;
}
