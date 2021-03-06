package com.webcrudsecurityboot.repository;

import com.webcrudsecurityboot.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User show(Long id);
    void save(User user);
    User update(User updatedUser);
    void delete(Long id);
    User findByName(String email);
    User getUserByName(String name);
}
