package com.epam.mentoring.service;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface GenericService<T> {

    T findById(Integer id) throws DataAccessException;

    Integer save(T object) throws DataAccessException;

    List<T> findAll() throws DataAccessException;

}
