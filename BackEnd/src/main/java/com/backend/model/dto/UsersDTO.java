package com.backend.model.dto;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class UsersDTO {
    private int userId;
    private int roleId;
    private String userName;
    private String firstName;
    private String lastName;
    private String emailUser;
    private String passwordUser;
    private UserRoleDTO role;

    private List<UsersArticReadedDTO> articlesReaded;
    private List<UsersArticCreatedDTO> articlesCreated;
    private List<UsersArticEditedDTO> articlesEdited;
}
