package com.backend.model;

import jakarta.persistence.*;
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
public class Users {

    @Id
    private int userId;
    private int roleId;
    private String userName;
    private String firstName;
    private String lastName;
    private String emailUser;
    private String passwordUser;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private UserRoles role;  // Un usuario tiene un rol

    @OneToMany(mappedBy = "user")
    private List<UsersArticReaded> articlesReaded;  // Un usuario puede leer varios artículos

    @OneToMany(mappedBy = "user")
    private List<UsersArticCreated> articlesCreated;  // Un usuario puede crear varios artículos

    @OneToMany(mappedBy = "user")
    private List<UsersArticEdited> articlesEdited;
}
