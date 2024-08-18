package com.backend.model.dto;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UsersDTO {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;

    @JsonIgnore
    private String password; // Dependiendo de si necesitas gestionar la contraseña

    private UserRoleDTO role;

    @JsonManagedReference
    @JsonIgnore// Relación para artículos creados
    private List<UsersArticlesCreatedDTO> articlesCreated;

    @JsonManagedReference // Relación para artículos editados
    @JsonIgnore
    private List<UsersArticlesEditedDTO> articlesEdited;

    @JsonManagedReference // Relación para artículos leídos
    @JsonIgnore
    private List<UsersArticlesReadedDTO> articlesReaded;
}
