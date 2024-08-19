package com.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleContentDTO {

    private Long id;
    private String content;

    @JsonBackReference
    @JsonIgnore// Indica que esta es la parte 'hija' de la relación
    private ArticlesDTO article;
}