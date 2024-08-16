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
public class UserRoles {

    @Id
    private int userRolesId;
    private String roleType;
    private String descriptionRol;

    // Relaciones
    @OneToMany(mappedBy = "role")
    private List<Users> users;
}
