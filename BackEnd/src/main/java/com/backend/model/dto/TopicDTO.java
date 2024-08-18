package com.backend.model.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDTO {

    private Long id;
    private String name;

    @JsonManagedReference // Parte 'padre' de la relación
    private List<ArticlesDTO> articles;
}
