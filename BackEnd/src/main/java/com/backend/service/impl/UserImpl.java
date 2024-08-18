package com.backend.service.impl;

import com.backend.model.Users;
import com.backend.repository.IUserRepo;
import com.backend.repository.IGenericRepo;
import com.backend.security.WebSecurityConfig;
import com.backend.service.IUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserImpl extends CrudImpl<Users, Integer> implements IUser {

    private final IUserRepo repo;

    @Override
    protected IGenericRepo<Users, Integer> getRepo() {
        return repo;
    }

    @Override
    public Users findByUserName(String username) {
        return repo.findByUserName(username);
    }

    @Override
    public Users save(Users users) throws Exception {
        String encode = WebSecurityConfig
                        .passwordEncoder()
                        .encode(users.getPassword());
        users.setPassword(encode);
        return repo.save(users);
    }

}
