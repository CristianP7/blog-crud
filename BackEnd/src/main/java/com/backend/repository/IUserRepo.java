package com.backend.repository;

import com.backend.model.Users;

public interface IUserRepo extends IGenericRepo<Users, Integer> {

    Users findByUserName(String username);
}