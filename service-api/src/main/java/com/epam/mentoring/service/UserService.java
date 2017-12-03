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
    int addUser(User user) throws DataAccessException;
    int updateUser(User user) throws DataAccessException;
    int deleteUser(int id) throws DataAccessException;
}
