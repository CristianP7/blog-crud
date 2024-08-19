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

    @Column(length = 60, nullable = false, unique = true)
    private String userName;

    @Column(length = 60, nullable = false)
    private String firstName;

    @Column(length = 60, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 60, nullable = false)
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
