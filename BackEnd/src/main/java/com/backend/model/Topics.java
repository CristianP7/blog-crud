package com.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Topics {

    @Id
    private int topicId;
    private String nameTopic;

    // Relaciones
    @OneToMany(mappedBy = "topic")
    private List<ArticleTopics> articleTopics;

}
