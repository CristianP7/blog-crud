package com.backend.model.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersArticlesCreatedDTO {
    private Integer id;

    @JsonBackReference // Indica que esta es la parte 'hija' de la relación
    private UsersDTO user;

    @JsonIgnore
    private ArticlesDTO article;

    private LocalDateTime createdAt;
}
