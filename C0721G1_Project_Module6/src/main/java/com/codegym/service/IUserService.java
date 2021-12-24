package com.codegym.service;

import com.codegym.model.User;

import java.util.Optional;

public interface IUserService extends IGenericService<User> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
