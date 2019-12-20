package com.spring.boot.security.jwtsecurity.service;

import com.spring.boot.security.jwtsecurity.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void delete(Long id);
    User findOne(String username);

    User findById(Long id);
}
