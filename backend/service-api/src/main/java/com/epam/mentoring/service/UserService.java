package com.epam.mentoring.service;

import com.epam.mentoring.data.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Interface for User service
 */
public interface UserService {
    User getUserById(int id) throws DataAccessException;

    List<User> getAllUsers() throws DataAccessException;

    Integer addUser(User user) throws DataAccessException;

    void updateUser(User user) throws DataAccessException;

    void deleteUser(int id) throws DataAccessException;
}
