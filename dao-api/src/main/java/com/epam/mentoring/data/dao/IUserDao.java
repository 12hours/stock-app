package com.epam.mentoring.data.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.User;

/**
 * Interface for {@code User} DAO;
 * Describes basic CRUD operations
 */
public interface IUserDao {

	/**
	 * Finds {@code User} record with specified id
	 * @param id target user id
	 * @return User
	 * @throws DataAccessException
	 */
	User getUserById(Integer id) throws DataAccessException;

	/**
	 * Finds all {@code User} records persisted in database
	 * @return list of users
	 * @throws DataAccessException
	 */
	List<User> getAllUsers() throws DataAccessException;

	/**
	 * Saves provided User to database
	 * @param user user to save
	 * @return not specified
	 * @throws DataAccessException
	 */
	int addUser(User user) throws DataAccessException;

	/**
	 * Updates User record;
	 * User record is identified by id field of provided {@code User} object
	 * @param user updated user
	 * @return not specified
	 * @throws DataAccessException
	 */
	int updateUser(User user) throws DataAccessException;

	/**
	 * Deletes User record with specified id from database
	 * @param id id of User record to delete
	 * @return not specified
	 * @throws DataAccessException
	 */
	int deleteUser(Integer id) throws DataAccessException;
}
