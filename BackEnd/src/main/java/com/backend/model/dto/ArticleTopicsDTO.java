package com.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleTopicsDTO {

    private Integer articleTopicsId;
    private Integer topicId;
    private Integer articleId;
    private TopicDTO topic;

    @JsonBackReference
    private ArticlesDTO article;
}
