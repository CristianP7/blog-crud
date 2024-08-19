package com.backend.service.impl;

import com.backend.model.Users;
import com.backend.repository.IUserRepo;
import com.backend.repository.IGenericRepo;
import com.backend.security.WebSecurityConfig;
import com.backend.service.IUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
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
        Users obj = new Users();
        String encode = WebSecurityConfig
                        .passwordEncoder()
                        .encode(users.getPassword());
        users.setPassword(encode);

        if(users.getRole().getId() == null || users.getRole().getId() == 0){
            users.getRole().setId(2);
        }
//        repo.findById(users.getId()).orElseThrow(() -> new Exception("ID NOT FOUND" + users.getId()));
        return repo.save(users);
    }

    @Override
    public Users update(Users users, Integer id) throws Exception {
        return getRepo().findById(id)
                .map(service -> {
                    service.setEmail(users.getEmail());
                    service.setFirstName(users.getFirstName());
                    service.setLastName(users.getLastName());
                    service.setUserName(users.getUserName());
                    service.setPassword(users.getPassword());
                    service.setRole(users.getRole());
                    return getRepo().save(service);
                }).orElseThrow(() -> new RuntimeException("Service not found with id " + id));
    }
}
