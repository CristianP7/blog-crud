package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRoles role;

    @OneToMany(mappedBy = "user")
    private List<UsersArticlesCreated> articlesCreated;

    @OneToMany(mappedBy = "user")
    private List<UsersArticlesEdited> articlesEdited;

    @OneToMany(mappedBy = "user")
    private List<UsersArticlesReaded> articlesReaded;
}
