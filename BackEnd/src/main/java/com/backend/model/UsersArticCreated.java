package com.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class UsersArticCreated {

    @Id
    private int userArticCreatedId;
    private int userId;
    private int articleId;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Users user;  // Un usuario puede crear varios artículos

    @ManyToOne
    @JoinColumn(name = "articleId", insertable = false, updatable = false)
    private Articles article;
}
