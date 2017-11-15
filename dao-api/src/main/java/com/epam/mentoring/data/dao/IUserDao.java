package com.epam.mentoring.data.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.User;

public interface IUserDao {
	User getUserById(Integer id) throws DataAccessException;
	List<User> getAllUsers() throws DataAccessException;
	int addUser(User user) throws DataAccessException;
	int updateUser(User user) throws DataAccessException;
	int deleteUser(Integer id) throws DataAccessException;
}
