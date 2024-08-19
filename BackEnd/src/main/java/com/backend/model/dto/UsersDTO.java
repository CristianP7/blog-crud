package com.backend.model.dto;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersDTO {

    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;

    @JsonIgnore
    private String password; // Dependiendo de si necesitas gestionar la contraseña

    @JsonIgnore
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
