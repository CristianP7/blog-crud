package com.backend.service;

import com.backend.model.Users;

public interface IUser extends ICrud<Users, Integer> {

    Users findByUserName(String username);

}
