package com.epam.mentoring.data.dao;

import java.util.List;

import com.epam.mentoring.data.model.User;

public interface IUserDao {
	User getUserById(int id);
	List<User> getAllUsers();
	int addUser(User user);
	int updateUser(User user);
	int deleteUser(int id);
}
