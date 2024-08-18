package com.backend.service;

import com.backend.model.Users;

public interface IUser {

    Users findByUserName(String username);

}
