package com.revotech.nsegen.dao;

import com.revotech.nsegen.exceptions.DAOException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Revotech on 05.07.16.
 */
public interface DAO<T> {
    Integer addEntity(T entity) throws DAOException;
    List<T> getEntities() throws DAOException;
    int deleteEntity(int id) throws DAOException;
    int updateEntity(T entity) throws DAOException;
}
