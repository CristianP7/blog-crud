package com.backend.service.impl;

import com.backend.model.UserRoles;
import com.backend.repository.IUserRolesRepo;
import com.backend.repository.IGenericRepo;
import com.backend.service.IArticle;
import com.backend.service.IUserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleImpl extends CrudImpl<UserRoles, Integer> implements IUserRoles {

    private final IUserRolesRepo repo;

    @Override
    protected IGenericRepo<UserRoles, Integer> getRepo() {
        return repo;
    }
}
